package com.example.shein.web;

import com.example.shein.model.binding.AlbumDTO.AlbumDTO;
import com.example.shein.model.binding.AlbumDTO.AlbumUpdateBindingModel;
import com.example.shein.model.service.AlbumService.AlbumAddServiceModel;
import com.example.shein.model.service.AlbumService.AlbumUpdateServiceModel;
import com.example.shein.model.view.AlbumDetailsViewModel;
import com.example.shein.service.AlbumService;
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
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ArtistService artistService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allAlbumsPage(Model model) {
        model.addAttribute("albums", albumService.getAllAlbums());
        return "albums";
    }


  @GetMapping("/details/{id}")
    public String showAlbum(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("albumDetails", this.albumService.findById(id,principal.getName()));
        return "albums-details";
    }
    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/add")
    public String addAlbum(Model model) {

        if (!model.containsAttribute("AlbumDTO")) {
            model.addAttribute("AlbumDTO", new AlbumDTO()).addAttribute("allArtists", artistService.getAllArtists());
        }
        return "albums-add";
    }

    @PostMapping("/add")
    public String addAlbumConfirm(@Valid AlbumDTO albumDTO,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("AlbumDTO", albumDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.AlbumDTO", bindingResult)
                    .addFlashAttribute("allArtists", artistService.getAllArtists());
            return "redirect:/albums/add";
        }

        AlbumAddServiceModel serviceModel = modelMapper.map(albumDTO, AlbumAddServiceModel.class);
        albumService.addAlbum(serviceModel);

        return "redirect:/albums/all";
    }

    @PreAuthorize("isAdmin(#id)")
    @DeleteMapping("/{id}")
    public String deleteAlbum(@PathVariable Long id, Principal principal) {

        albumService.deleteAlbum(id);

        return "redirect:/albums/all";
    }
    @PreAuthorize("isAdmin(#id)")
    @GetMapping("/{id}/edit")
    public String editAlbum(@PathVariable Long id, Model model,
                               @AuthenticationPrincipal SheinUser currentUser) {

        AlbumDetailsViewModel albumDetailsViewModel = albumService.findById(id, currentUser.getUserIdentifier());
        AlbumDTO albumDTO = modelMapper.map(
                albumDetailsViewModel,
                AlbumDTO.class
        );
        model.addAttribute("albumDTO", albumDTO);
        return "albums-update";
    }

    @GetMapping("/edit/{id}/errors")
    public String editAlbumErrors(@PathVariable Long id, Model model) {
        return "albums-update";
    }

    @PatchMapping("/{id}/edit")
    public String editAlbum(
            @PathVariable Long id,
            @Valid AlbumUpdateBindingModel albumUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws ObjectNotFoundException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("albumDTO", albumUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumDTO", bindingResult);

            return "redirect:/albums/edit/" + id + "/errors";
        }

        AlbumUpdateServiceModel serviceModel = modelMapper.map(albumUpdateBindingModel,
                AlbumUpdateServiceModel.class);
        serviceModel.setId(id);

        albumService.updateAlbum(serviceModel);

        return "redirect:/albums/all";
    }

}
