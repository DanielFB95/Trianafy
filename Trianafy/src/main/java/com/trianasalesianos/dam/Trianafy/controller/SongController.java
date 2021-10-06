package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.dto.GetSongDto;
import com.trianasalesianos.dam.Trianafy.dto.SongDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {


    private final SongRepository songRepository;
    private final SongDtoConverter dtoConverter;


    @GetMapping("/")
    public ResponseEntity<List<GetSongDto>> findAll() {

        List<Song> data = songRepository.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<GetSongDto> result =
                    data.stream()
                            .map(dtoConverter::songToGetSongDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }
    }


    /**
     * Buscar cancion por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id) {

        return ResponseEntity.of(songRepository.findById(id));


    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> edit(@RequestBody CreateSongDto s, @PathVariable Long id) {


        return ResponseEntity.of(songRepository.findById(id).map(c -> {
                    c.setAlbum(s.getAlbum());
                    c.setTitle(s.getTitle());
                    c.setYear(s.getYear());
                    songRepository.save(c);
                    return c;
                })
        );


    }

    @PostMapping("/")
    public ResponseEntity<Song> create(@RequestBody CreateSongDto newSong) {


        Song s = dtoConverter.createSongDtoToSong(newSong);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songRepository.save(s));
    }

}
