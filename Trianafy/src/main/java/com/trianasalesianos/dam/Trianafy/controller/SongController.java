package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.dto.GetSongDto;
import com.trianasalesianos.dam.Trianafy.dto.SongDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {


    private final SongRepository repository;
    private final SongDtoConverter dtoConverter;


    /**
     * Buscar cancion por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Song> findOne(@PathVariable Long id){

        return ResponseEntity.of(repository.findById(id));


    }

    @PutMapping("/{id}")
    public ResponseEntity<GetSongDto> edit(@RequestBody Song s, @PathVariable Long id){

        Optional<Song> song = repository.findById(id).map(c -> {
                                    c.setTitle(s.getTitle());
                                    c.setArtist(s.getArtist());
                                    c.setAlbum(s.getAlbum());
                                    c.setYear(s.getYear());
                                    repository.save(c);
                                    return c;
        });

        GetSongDto gsd = dtoConverter.songToGetSongDto(Optional.of(song));
        return ResponseEntity.ok().body(gsd);
    }

}
