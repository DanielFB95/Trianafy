package com.trianasalesianos.dam.Trianafy.data;

import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final SongRepository songRepo;
    private final ArtistRepository artistRepo;
    private final PlaylistRepository playlistRepo;


    @PostConstruct
    public void init() {

        artistRepo.saveAll(

                Arrays.asList(
                        new Artist("Lil Nas X"),
                        new Artist("Twenty One Pilots"),
                        new Artist("Billie Eilish")
                )
        );

        songRepo.saveAll(

                Arrays.asList(
                        new Song("Jumpsuit", artistRepo.getById(2L), "Trench", "2018"),
                        new Song("Call me by your Name", artistRepo.getById(1L), "Montero", "2021"),
                        new Song("Bad Guy", artistRepo.getById(3L), "When we fall asleep, where do we go?", "2019")
                )

        );



        playlistRepo.save(
                new Playlist("Una playlist", "Esta es una playlist de prueba", Arrays.asList(songRepo.getById(2L), songRepo.getById(3L)))
                );


    }


}
