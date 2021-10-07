package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.dto.GetSongDto;
import com.trianasalesianos.dam.Trianafy.dto.SongDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
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
    private final ArtistRepository artistRepository;


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
   // @GetMapping(value = {"/{id}", "/lists/{id}/songs/{id2}"})
    @GetMapping("/{id}")
    public ResponseEntity<GetSongDto> findOne(@PathVariable Long id) {

        GetSongDto dto = dtoConverter.songToGetSongDto(songRepository.getById(id));

        return ResponseEntity.ok().body(dto);
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

        Artist a = artistRepository.findById(newSong.getArtistId()).orElse(null);

        s.setArtist(a);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songRepository.save(s));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        songRepository.deleteById(id);

        return ResponseEntity.noContent()
                .build();


    }

}
