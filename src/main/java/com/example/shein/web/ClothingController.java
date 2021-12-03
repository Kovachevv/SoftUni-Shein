package com.example.shein.web;

import com.example.shein.service.ClothingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clothes")
public class ClothingController {


    private final ClothingService clothingService;

    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping("/all")
    public String allClothesPage(Model model){
        model.addAttribute("AllClothes", clothingService.getAllClothes());
        return "clothes";
    }

    @GetMapping("/details/{id}")
    public String showClothing(@PathVariable Long id, Model model) {
        model.addAttribute("clothingDetails", this.clothingService.findById(id));
        return "clothes-details";
    }

}
