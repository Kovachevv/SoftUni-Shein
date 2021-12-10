package com.example.shein.web;

import com.example.shein.model.binding.ClothesDTO;
import com.example.shein.model.service.ClothesService.ClothesAddServiceModel;
import com.example.shein.model.service.ClothesService.ClothesUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.service.BrandService;
import com.example.shein.service.ClothingService;
import com.example.shein.service.impl.SheinUser;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/clothes")
public class ClothingController {


    private final ClothingService clothingService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public ClothingController(ClothingService clothingService, ModelMapper modelMapper, BrandService brandService) {
        this.clothingService = clothingService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String allClothesPage(Model model) {
        model.addAttribute("AllClothes", clothingService.getAllClothes());
        return "clothes";
    }

    @GetMapping("/details/{id}")
    public String showClothing(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("clothingDetails", this.clothingService.findById(id,principal.getName()));
        return "clothes-details";
    }

    @GetMapping("/add")
    public String addClothing(Model model) {

        if (!model.containsAttribute("ClothesDTO")) {
            model.addAttribute("ClothesDTO", new ClothesDTO()).
                    addAttribute("clothingBrands", brandService.getAllBrands());
        }
        return "clothes-add";
    }

    @PostMapping("/add")
    public String addClothingConfirm(@Valid ClothesDTO clothesDTO,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ClothesDTO", clothesDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.ClothesDTO", bindingResult)
                    .addFlashAttribute("clothingBrands", brandService.getAllBrands());
            return "redirect:/clothes/add";
        }

        ClothesAddServiceModel clothesAddServiceModel = modelMapper.map(clothesDTO, ClothesAddServiceModel.class);
        clothingService.addClothing(clothesAddServiceModel);

        return "redirect:/clothes/all";
    }


    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteClothing(@PathVariable Long id, Principal principal) {

        clothingService.deleteClothing(id);

        return "redirect:/clothes/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/{id}/edit")
    public String editClothing(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal SheinUser currentUser) {

        ClothingDetailsView clothingDetailsView = clothingService.findById(id, currentUser.getUserIdentifier());
        ClothesDTO clothingModel = modelMapper.map(
                clothingDetailsView,
                ClothesDTO.class
        );
        model.addAttribute("clothingModel", clothingModel);
        return "clothes-update";
    }

    @GetMapping("/edit/{id}/errors")
    public String editClothingErrors(@PathVariable Long id, Model model) {
        return "clothes-update";
    }

    @PreAuthorize("isAdmin(#id)")
    @PatchMapping("/{id}/edit")
    public String editClothing(
            @PathVariable Long id,
            @Valid ClothesDTO clothesDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("clothingModel", clothesDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.clothingModel", bindingResult);

            return "redirect:/clothes/edit/" + id + "/errors";
        }
        ClothesUpdateServiceModel serviceModel = modelMapper.map(clothesDTO,
                ClothesUpdateServiceModel.class);
        serviceModel.setId(id);

        clothingService.updateClothing(serviceModel);

        return "redirect:/clothes/all";
    }

}
