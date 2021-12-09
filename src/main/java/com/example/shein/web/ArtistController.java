package com.example.shein.web;

import com.example.shein.model.binding.ArtistDTO;
import com.example.shein.model.service.ArtistService.ArtistAddServiceModel;
import com.example.shein.model.service.ArtistService.ArtistUpdateServiceModel;
import com.example.shein.model.service.BrandAddServiceModel;
import com.example.shein.model.view.ArtistDetailsViewModel;
import com.example.shein.service.ArtistService;
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
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final ModelMapper modelMapper;


    public ArtistController(ArtistService artistService, ModelMapper modelMapper) {
        this.artistService = artistService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String getAllArtists(Model model) {
        model.addAttribute("artists",
                artistService.getAllArtists());
        return "artists";
    }

    @GetMapping("/details/{id}")
    public String showArtists(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("artistDetails", this.artistService.findById(id, principal.getName()));
        return "artists-details";
    }


    @GetMapping("/add")
    public String addArtist(Model model) {

        if (!model.containsAttribute("ArtistDTO")) {
            model.addAttribute("ArtistDTO", new ArtistDTO());
        }
        return "artists-add";
    }

    @PostMapping("/add")
    public String addArtistConfirm(@Valid ArtistDTO artistDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ArtistDTO", artistDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.ArtistDTO", bindingResult);
            return "artists-add";
        }

        ArtistAddServiceModel artistAddServiceModel = modelMapper.map(artistDTO, ArtistAddServiceModel.class);
        artistService.addArtist(artistAddServiceModel);

        return "redirect:/artists/all";
    }


    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteArtist(@PathVariable Long id, Principal principal) {

        artistService.deleteArtist(id);

        return "redirect:/artists/all";
    }

    @GetMapping("/{id}/edit")
    public String editArtist(@PathVariable Long id, Model model,
                            @AuthenticationPrincipal SheinUser currentUser) {

        ArtistDetailsViewModel artistDetailsViewModel = artistService.findById(id, currentUser.getUserIdentifier());
        ArtistDTO artistDTO = modelMapper.map(artistDetailsViewModel, ArtistDTO.class);

        model.addAttribute("artistModel", artistDTO);
        return "artists-update";

    }

    @GetMapping("/edit/{id}/errors")
    public String editArtistErrors(@PathVariable Long id, Model model) {
        return "artists-update";
    }

    @PatchMapping("/{id}/edit")
    public String editArtist(
            @PathVariable Long id,
            @Valid ArtistDTO artistDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("artistModel", artistDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.artistModel", bindingResult);

            return "redirect:/artists/edit/" + id + "/errors";
        }

        ArtistUpdateServiceModel serviceModel = modelMapper.map(artistDTO,
                ArtistUpdateServiceModel.class);
        serviceModel.setId(id);

        artistService.updateArtist(serviceModel);

        return "redirect:/artists/all";
    }
}
