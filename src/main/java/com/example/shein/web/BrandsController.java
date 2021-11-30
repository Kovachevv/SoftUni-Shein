package com.example.shein.web;


import com.example.shein.model.view.BrandViewModel;
import com.example.shein.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

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
        List<BrandViewModel> brandViewModels = brandService.getAllBrands().stream()
                .map(brandServiceModel -> modelMapper.map(brandServiceModel, BrandViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("allBrands", brandViewModels);
        return "brands";
    }

    @GetMapping("/brands/{id}/details")
    public String showBrand(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("brandDetails", this.brandService.findById(id, principal.getName()));
        return "details";
    }
}
