package com.example.shein.web;

import com.example.shein.model.binding.AccessoryDTO;
import com.example.shein.model.service.AccessoryService.AccessoryAddServiceModel;
import com.example.shein.model.service.AccessoryService.AccessoryUpdateServiceModel;
import com.example.shein.model.view.AccessoryDetailsViewModel;
import com.example.shein.service.AccessoryService;
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
@RequestMapping("/accessories")
public class AccessoryController {

    private final AccessoryService accessoryService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public AccessoryController(AccessoryService accessoryService, ModelMapper modelMapper, BrandService brandService) {
        this.accessoryService = accessoryService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public String allAccessoriesPage(Model model) {
        model.addAttribute("AllAccessories", accessoryService.getAllAccessories());
        return "accessories";
    }

    @GetMapping("/details/{id}")
    public String showAccessory(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("accessoryDetails", this.accessoryService.findById(id,principal.getName()));
        return "accessories-details";
    }

   @GetMapping("/add")
    public String addAccessory(Model model) {

        if (!model.containsAttribute("AccessoryDTO")) {
            model.addAttribute("AccessoryDTO", new AccessoryDTO()).
                    addAttribute("accessoryBrands", brandService.getAllBrands());
        }
        return "accessories-add";
    }

    @PostMapping("/add")
    public String addAccessoryConfirm(@Valid AccessoryDTO accessoryDTO,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("AccessoryDTO", accessoryDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.AccessoryDTO", bindingResult)
                    .addFlashAttribute("accessoryBrands", brandService.getAllBrands());
            return "accessories-add";
        }

        AccessoryAddServiceModel accessoryAddServiceModel = modelMapper.map(accessoryDTO, AccessoryAddServiceModel.class);
        accessoryService.addAccessory(accessoryAddServiceModel);

        return "redirect:/accessories/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteAccessory(@PathVariable Long id, Principal principal) {

        accessoryService.deleteAccessory(id);

        return "redirect:/accessories/all";
    }
    @GetMapping("/{id}/edit")
    public String editAccessory(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal SheinUser currentUser) {

        AccessoryDetailsViewModel accessoryDetailsViewModel = accessoryService.findById(id, currentUser.getUserIdentifier());
        AccessoryDTO accessoryModel = modelMapper.map(
                accessoryDetailsViewModel,
                AccessoryDTO.class
        );
        model.addAttribute("AccessoryModel", accessoryModel);
        return "accessories-update";
    }

    @GetMapping("/edit/{id}/errors")
    public String editAccessoryErrors(@PathVariable Long id, Model model) {
        return "accessories-update";
    }

    @PatchMapping("/{id}/edit")
    public String editAccessory(
            @PathVariable Long id,
            @Valid AccessoryDTO accessoryDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("AccessoryModel", accessoryDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.AccessoryModel", bindingResult);

            return "redirect:/accessories/edit/" + id + "/errors";
        }
        AccessoryUpdateServiceModel serviceModel = modelMapper.map(accessoryDTO,
                AccessoryUpdateServiceModel.class);
        serviceModel.setId(id);

        accessoryService.updateAccessory(serviceModel);

        return "redirect:/accessories/all";
    }

}
