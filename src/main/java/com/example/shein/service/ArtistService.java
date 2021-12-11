package com.example.shein.service;

import com.example.shein.model.service.ArtistService.ArtistAddServiceModel;
import com.example.shein.model.service.ArtistService.ArtistUpdateServiceModel;
import com.example.shein.model.view.ArtistDetailsViewModel;
import com.example.shein.model.view.ArtistViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface ArtistService {


    List<ArtistViewModel> getAllArtists();

    void initArtists();

    ArtistDetailsViewModel findById(Long id, String name);

    void addArtist(ArtistAddServiceModel serviceModel);

    void deleteArtist(Long id);

    void updateArtist(ArtistUpdateServiceModel serviceModel) throws ObjectNotFoundException;
}
