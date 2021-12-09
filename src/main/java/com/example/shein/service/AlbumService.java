package com.example.shein.service;

import com.example.shein.model.service.AlbumService.AlbumAddServiceModel;
import com.example.shein.model.service.AlbumService.AlbumUpdateServiceModel;
import com.example.shein.model.view.AlbumDetailsViewModel;
import com.example.shein.model.view.AlbumViewModel;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.*;

public interface AlbumService {
    List<AlbumViewModel> getAllAlbums();
    void initAlbums();

    AlbumDetailsViewModel findById(Long id, String name);

    void addAlbum(AlbumAddServiceModel serviceModel);

    void deleteAlbum(Long id);

    void updateAlbum(AlbumUpdateServiceModel serviceModel) throws ObjectNotFoundException;
}
