package com.example.shein.service;

import com.example.shein.model.entity.ArtistEntity;
import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.ArtistService.ArtistAddServiceModel;
import com.example.shein.model.service.ArtistService.ArtistUpdateServiceModel;
import com.example.shein.model.view.ArtistViewModel;
import com.example.shein.repository.ArtistRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.repository.UserRoleRepository;
import com.example.shein.service.impl.ArtistServiceImpl;
import com.example.shein.service.impl.BrandServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    private ArtistServiceImpl artistService;
    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private ArtistEntity artistEntity;
    private ArtistViewModel artistViewModel;
    private ArtistAddServiceModel artistAddServiceModel;
    private ArtistUpdateServiceModel artistUpdateServiceModel;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        artistService = new ArtistServiceImpl(artistRepository, modelMapper, userRepository);
        artistEntity = new ArtistEntity();
        artistEntity.setName("Test").setDescription("Long test description").setImageUrl("TestImageUrl");
        artistViewModel = new ArtistViewModel();
        artistAddServiceModel = new ArtistAddServiceModel();
        artistUpdateServiceModel = new ArtistUpdateServiceModel();

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        artistRepository.deleteAll();
    }

    @Disabled
    @Test
    void getAllArtistsTest(){

        Mockito.when(artistRepository.findAll()).thenReturn(List.of(artistEntity));
       Mockito.when(artistRepository.findAll().stream().map(artistEntity -> {
            return this.modelMapper.map(artistEntity, ArtistViewModel.class);
        }).collect(Collectors.toList())).thenReturn(List.of(artistViewModel));


        List<ArtistViewModel> expected = artistService.getAllArtists();

        List<ArtistViewModel> actual = List.of(artistViewModel);


        Assertions.assertEquals(expected,actual);


    }
    @Test
    void addArtistTest(){

        artistService.addArtist(artistAddServiceModel);
        artistEntity = modelMapper.map(artistAddServiceModel, ArtistEntity.class);
        ArgumentCaptor<ArtistEntity> argumentCaptor =
                ArgumentCaptor.forClass(ArtistEntity.class);
        Mockito.verify(artistRepository).save(argumentCaptor.capture());
    }

    @Test
    void deleteArtistTest(){

        artistService.deleteArtist(artistEntity.getId());
        Assertions.assertEquals(0,artistRepository.count());
    }


    @Test
    void updateArtistTest() throws ObjectNotFoundException {

        Mockito.when(artistRepository.findById(artistUpdateServiceModel.getId())).thenReturn(Optional.of(artistEntity));

        artistService.updateArtist(artistUpdateServiceModel);
        artistEntity.setName(artistUpdateServiceModel.getName()).setImageUrl(artistUpdateServiceModel.getImageUrl()).
                setDescription(artistUpdateServiceModel.getDescription());

        ArgumentCaptor<ArtistEntity> argumentCaptor =
                ArgumentCaptor.forClass(ArtistEntity.class);

        Mockito.verify(artistRepository).save(argumentCaptor.capture());
    }

    @Disabled
    @Test
    void updateArtistFailsTest() throws ObjectNotFoundException {

        Mockito.when(artistRepository.findById(artistUpdateServiceModel.getId())).thenThrow(ObjectNotFoundException.class);
        Optional<ArtistEntity> actual = artistRepository.findById(artistUpdateServiceModel.getId());

        ObjectNotFoundException expected = new ObjectNotFoundException("Artist not found!");
    }

    @Test
    void isAdminTest(){
        Mockito.when(userRepository.findByUsername(userEntity.getUsername())).thenReturn(Optional.of(userEntity));
        boolean admin = artistService.isAdmin(userEntity.getUsername());

        Assertions.assertFalse(admin);
    }
    @Disabled
    @Test
    void isAdminNullTest(){
        UserEntity user = null;
        Mockito.when(userRepository.findByUsername(userEntity.getUsername())).thenReturn(Optional.of(user));
        artistService.isAdmin(userEntity.getUsername());
        Assertions.assertFalse(artistService.isAdmin(userEntity.getUsername()));
    }
    @Test
    void initArtistsTest(){

        Mockito.when(artistRepository.count() == 0).thenReturn(true);

        artistService.initArtists();



    }



}


