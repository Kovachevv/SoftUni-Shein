package com.example.shein.web;


import com.example.shein.model.binding.BrandDTO;
import com.example.shein.model.service.BrandService.BrandAddServiceModel;
import com.example.shein.model.service.BrandService.BrandUpdateServiceModel;
import com.example.shein.model.view.BrandDetailsViewModel;
import com.example.shein.service.BrandService;
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
@RequestMapping("/brands")
public class BrandsController {

    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public BrandsController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String getAllBrandsPage(Model model) {
        model.addAttribute("brands",
                brandService.getAllBrands());
        return "brands";
    }

    @GetMapping("/details/{id}")
    public String showBrand(@PathVariable Long id, Model model,Principal principal) {
        model.addAttribute("brandDetails", this.brandService.findById(id, principal.getName()));
        return "brand-details";
    }

    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/add")
    public String addBrand(Model model) {

        if (!model.containsAttribute("BrandDTO")) {
            model.addAttribute("BrandDTO", new BrandDTO());
        }
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrandConfirm(@Valid BrandDTO brandDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("BrandDTO", brandDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.BrandDTO", bindingResult);
            return "brand-add";
        }

        BrandAddServiceModel brandAddServiceModel = modelMapper.map(brandDTO, BrandAddServiceModel.class);
        brandService.addBrand(brandAddServiceModel);

        return "redirect:/brands/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable Long id, Principal principal) {

        brandService.deleteBrand(id);

        return "redirect:/brands/all";
    }
    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/{id}/edit")
    public String editBrand(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal SheinUser currentUser) {

        BrandDetailsViewModel brandDetailsViewModel = brandService.findById(id, currentUser.getUserIdentifier());
        BrandDTO brandModel = modelMapper.map(
                brandDetailsViewModel,
                BrandDTO.class
        );

        model.addAttribute("brandModel", brandModel);
        return "brand-update";

    }

    @GetMapping("/edit/{id}/errors")
    public String editBrandErrors(@PathVariable Long id, Model model) {
        return "brand-update";
    }

    @PreAuthorize("isAdmin(#id)")
    @PatchMapping("/{id}/edit")
    public String editBrand(
            @PathVariable Long id,
            @Valid BrandDTO brandDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("brandModel", brandDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.brandModel", bindingResult);

            return "redirect:/brands/edit/" + id + "/errors";
        }

        BrandUpdateServiceModel serviceModel = modelMapper.map(brandDTO,
                BrandUpdateServiceModel.class);
        serviceModel.setId(id);

        brandService.updateBrand(serviceModel);

        return "redirect:/brands/all";
    }
}

