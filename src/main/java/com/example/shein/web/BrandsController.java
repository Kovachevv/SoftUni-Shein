package com.example.shein.web;


import com.example.shein.model.binding.BrandDTO;
import com.example.shein.model.service.BrandAddServiceModel;
import com.example.shein.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String showBrand(@PathVariable Long id, Model model) {
        model.addAttribute("brandDetails", this.brandService.findById(id));
        return "brand-details";
    }

    @GetMapping("/add")
    public String addBrand(Model model){

        if(!model.containsAttribute("BrandDTO")){
            model.addAttribute("BrandDTO", new BrandDTO());
        }
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrandConfirm(@Valid BrandDTO brandDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("BrandDTO", brandDTO)
            .addFlashAttribute("org.springframework.validation.BindingResult.BrandDTO", bindingResult);
            return "redirect:/brands/add";
        }

        BrandAddServiceModel brandAddServiceModel = modelMapper.map(brandDTO,BrandAddServiceModel.class);
        brandService.addBrand(brandAddServiceModel);

        return "redirect:/";
    }





}
