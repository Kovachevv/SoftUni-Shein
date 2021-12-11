package com.example.shein.service.impl;

import com.example.shein.model.entity.*;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.AlbumService.AlbumAddServiceModel;
import com.example.shein.model.service.AlbumService.AlbumUpdateServiceModel;
import com.example.shein.model.view.AlbumDetailsViewModel;
import com.example.shein.model.view.AlbumViewModel;
import com.example.shein.repository.AlbumRepository;
import com.example.shein.repository.ArtistRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.AlbumService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {


    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ModelMapper modelMapper, ArtistRepository artistRepository, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<AlbumViewModel> getAllAlbums() {
        return albumRepository.findAll().stream().map(albumEntity -> this.modelMapper.map(albumEntity, AlbumViewModel.class)).collect(Collectors.toList());

    }

    @Override
    public void initAlbums() {
        if (albumRepository.count() == 0) {

            ArtistEntity kanyeWest = artistRepository.findByName("Kanye West").orElseThrow(IllegalArgumentException::new);
            ArtistEntity travisScott = artistRepository.findByName("Travis Scott").orElseThrow(IllegalArgumentException::new);

            AlbumEntity ye = new AlbumEntity();
            ye.setName("ye").setArtist(kanyeWest).setImageUrl("https://upload.wikimedia.org/wikipedia/en/7/74/Ye_album_cover.jpg").setDescription("Ye paints a vivid picture of Kanye's current state of self and, for as brief as it is, succeeds as an intimate exploration of family and mental health.");
            AlbumEntity astroworld = new AlbumEntity();
            astroworld.setName("Astroworld").setArtist(travisScott).setImageUrl("https://upload.wikimedia.org/wikipedia/en/0/0b/Astroworld_by_Travis_Scott.jpg").setDescription("Travis Scott continues pushing the psychedelic boundaries of trap on Astroworld, but doesn't quite stick the landing.");

            AlbumEntity yeezus = new AlbumEntity();
            yeezus.setName("Yeezus").setImageUrl("https://upload.wikimedia.org/wikipedia/en/0/03/Yeezus_album_cover.png").setArtist(kanyeWest).
                    setDescription("On Yeezus, Chicago rapper and producer Kanye West takes his typically ambitious production down some really dark, noisy passageways.");

            AlbumEntity birds = new AlbumEntity();
            birds.setArtist(travisScott).setName("Birds in the Trap").setImageUrl("https://upload.wikimedia.org/wikipedia/en/6/61/Travis_Scott_-_Birds_in_the_Trap_Sing_McKnight.png").setDescription("Travis Scott returns with what sounds like an album of microwaved leftovers from his breakout record last year, Rodeo.");

            albumRepository.saveAll(List.of(ye, astroworld,yeezus,birds));


        }
    }
    public boolean isAdmin(String userName) {
        UserEntity user = userRepository.findByUsername(userName).orElse(null);
        if (user == null) {
            return false;
        }
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public AlbumDetailsViewModel findById(Long id, String name) {
        return albumRepository.findById(id).map(albumEntity -> {
            AlbumDetailsViewModel albumDetailsViewModel = this.modelMapper.map(albumEntity, AlbumDetailsViewModel.class);
            albumDetailsViewModel.setCanDelete(isAdmin(name)).setArtist(albumEntity.getArtist().getName());
            return albumDetailsViewModel;
        }).get();
    }

    @Override
    public void addAlbum(AlbumAddServiceModel serviceModel) {

        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setName(serviceModel.getName()).setDescription(serviceModel.getDescription()).
        setArtist(serviceModel.getArtist()).setImageUrl(serviceModel.getImageUrl());
        albumRepository.save(albumEntity);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void updateAlbum(AlbumUpdateServiceModel serviceModel) throws ObjectNotFoundException {

        AlbumEntity albumEntity = albumRepository.findById(serviceModel.getId()).orElseThrow(()->
                new ObjectNotFoundException("Album with id " + serviceModel.getId() + " not found!"));
        albumEntity.setName(serviceModel.getName()).setImageUrl(serviceModel.getImageUrl()).
                setDescription(serviceModel.getDescription());
     albumRepository.save(albumEntity);
    }
}
