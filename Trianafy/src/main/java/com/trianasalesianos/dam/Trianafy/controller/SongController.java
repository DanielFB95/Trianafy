package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SongController {


    private final SongRepository repository;
    private final SongDtoConverter dtoConverter;

    

    @PostMapping("/")

    public ResponseEntity<Song> create(@RequestBody CreateSongDto newSong) {



        Song newSong = dtoConverter.createSongDtoToSong(newSong);



        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(newSong));
    }
}
