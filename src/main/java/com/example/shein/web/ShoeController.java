package com.example.shein.web;

import com.example.shein.model.binding.ClothesDTO;
import com.example.shein.model.binding.ShoeDTO;
import com.example.shein.model.service.ClothesService.ClothesUpdateServiceModel;
import com.example.shein.model.service.ShoeService.ShoeAddServiceModel;
import com.example.shein.model.service.ShoeService.ShoeUpdateServiceModel;
import com.example.shein.model.view.ClothingDetailsView;
import com.example.shein.model.view.ShoeDetailsView;
import com.example.shein.service.BrandService;
import com.example.shein.service.ShoeService;
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
@RequestMapping("/shoes")
public class ShoeController {

    private final ShoeService shoeService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public ShoeController(ShoeService shoeService, BrandService brandService, ModelMapper modelMapper) {
        this.shoeService = shoeService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String getAllShoesPage(Model model) {
        model.addAttribute("shoes",
                shoeService.getAllShoes());
        return "shoes";
    }

    @GetMapping("/details/{id}")
    public String showShoe(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("shoeDetails", this.shoeService.findById(id, principal.getName()));
        return "shoes-details";
    }

    @GetMapping("/add")
    public String addShoe(Model model) {
        if (!model.containsAttribute("ShoesDTO")) {
            model.addAttribute("ShoesDTO", new ClothesDTO()).
                    addAttribute("shoeBrands", brandService.getAllBrands());
        }
        return "shoes-add";
    }

    @PostMapping("/add")
    public String addShoeConfirm(@Valid ShoeDTO shoeDTO,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ShoesDTO", shoeDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.ShoesDTO", bindingResult)
                    .addFlashAttribute("shoeBrands", brandService.getAllBrands());
            return "clothes-add";
        }

        ShoeAddServiceModel serviceModel = modelMapper.map(shoeDTO, ShoeAddServiceModel.class);
        shoeService.addShoe(serviceModel);

        return "redirect:/shoes/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteClothing(@PathVariable Long id, Principal principal) {

        shoeService.deleteShoe(id);

        return "redirect:/shoes/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/{id}/edit")
    public String editShoe(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal SheinUser currentUser) {

        ShoeDetailsView shoeDetailsView = shoeService.findById(id, currentUser.getUserIdentifier());
        ShoeDTO shoeDTO = modelMapper.map(
                shoeDetailsView,
                ShoeDTO.class
        );
        model.addAttribute("shoeModel", shoeDTO);
        return "shoes-update";
    }

    @GetMapping("/edit/{id}/errors")
    public String editShoeErrors(@PathVariable Long id, Model model) {
        return "shoes-update";
    }

    @PatchMapping("/{id}/edit")
    public String editShoe(
            @PathVariable Long id,
            @Valid ShoeDTO shoeDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shoeModel", shoeDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shoeModel", bindingResult);
            return "redirect:/shoes/edit/" + id + "/errors";
        }
        ShoeUpdateServiceModel serviceModel = modelMapper.map(shoeDTO,
                ShoeUpdateServiceModel.class);
        serviceModel.setId(id);

        shoeService.updateShoe(serviceModel);

        return "redirect:/shoes/all";
    }
}
