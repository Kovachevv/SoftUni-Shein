package com.example.shein.service.impl;

import com.example.shein.model.entity.ArtistEntity;
import com.example.shein.model.entity.BrandEntity;
import com.example.shein.model.entity.UserEntity;
import com.example.shein.model.entity.UserRoleEntity;
import com.example.shein.model.enums.UserRoleEnum;
import com.example.shein.model.service.ArtistService.ArtistAddServiceModel;
import com.example.shein.model.service.ArtistService.ArtistUpdateServiceModel;
import com.example.shein.model.view.ArtistDetailsViewModel;
import com.example.shein.model.view.ArtistViewModel;
import com.example.shein.repository.ArtistRepository;
import com.example.shein.repository.UserRepository;
import com.example.shein.service.ArtistService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {


    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public List<ArtistViewModel> getAllArtists() {
        return artistRepository.findAll().stream().map(artistEntity -> {
            return this.modelMapper.map(artistEntity, ArtistViewModel.class);
        }).collect(Collectors.toList());
    }

    @Override
    public void initArtists() {

        if (artistRepository.count() == 0) {
            ArtistEntity kanyeWest = new ArtistEntity();
            kanyeWest.setName("Kanye West").setImageUrl("https://media.voguebusiness.com/photos/5ef6493adf1073db3375835d/master/pass/kanye-west-gap-news-voguebus-mert-alas-and-marcus-piggott-june-20-story.jpg").setDescription("Kanye West, legal name Ye, (born June 8, 1977, Atlanta, Georgia, U.S.), American producer, rapper, and fashion designer who parlayed his production success in the late 1990s and early 2000s into a career as a popular, critically acclaimed solo artist.");
            ArtistEntity travisScott = new ArtistEntity();
            travisScott.setName("Travis Scott").setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Travis_Scott_-_Openair_Frauenfeld_2019_08.jpg/330px-Travis_Scott_-_Openair_Frauenfeld_2019_08.jpg").setDescription("Travis Scott is an American rapper, singer and songwriter. Born as Jacques Webster, he gained limelight in 2012 when he signed his first major label-deal with Epic Records. Not looking back, he ended the year on a high note by inking a deal with Kanye West’s GOOD Music’s, VERY GOOD Beats. To mark his association, he was seen in the label’s 2012 compilation ‘Cruel Summer’. In April 2013, Scott signed a record deal with T.I.'s Grand Hustle imprint as well.");

            ArtistEntity pharrell = new ArtistEntity();
            pharrell.setName("Pharell Williams").setImageUrl("https://www.biography.com/.image/t_share/MTQ3NjM5NjAyMDAwMTc2MjAy/pharrell_williams_photo_by_brian_bowen_nbcuniversal_getty_462041114.jpg").setDescription("American musician who was involved in a number of pop hits as part of the producing team the Neptunes, as a songwriter, and as a solo performer.");

            ArtistEntity kendrick = new ArtistEntity();
            kendrick.setName("Kendrick Lamar").setImageUrl("https://s26162.pcdn.co/wp-content/uploads/2021/04/the-meaning-of-the-lyrics-of-kendrick-lamar-songs-explained-1200x640-1.jpeg").setDescription("American rapper who achieved critical and commercial success with such albums as good kid, m.A.A.d city (2012) and To Pimp a Butterfly (2015).");
            artistRepository.saveAll(List.of(kanyeWest, pharrell,travisScott,kendrick));

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
    public ArtistDetailsViewModel findById(Long id, String name) {

        return artistRepository.findById(id).map(artistEntity -> {
            ArtistDetailsViewModel artistDetailsViewModel = this.modelMapper.map(artistEntity, ArtistDetailsViewModel.class);
            artistDetailsViewModel.setCanDelete(isAdmin(name));
            return artistDetailsViewModel;
        }).get();
    }

    @Override
    public void addArtist(ArtistAddServiceModel serviceModel) {
        ArtistEntity artistEntity = modelMapper.map(serviceModel, ArtistEntity.class);
        artistRepository.save(artistEntity);
    }

    @Override
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }


    @Override
    public void updateArtist(ArtistUpdateServiceModel serviceModel) throws ObjectNotFoundException {
        ArtistEntity artistEntity = artistRepository.findById(serviceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Artist not found!"));
        artistEntity.setName(serviceModel.getName()).
                setDescription(serviceModel.getDescription()).setImageUrl(serviceModel.getImageUrl());
        artistRepository.save(artistEntity);
    }
}
