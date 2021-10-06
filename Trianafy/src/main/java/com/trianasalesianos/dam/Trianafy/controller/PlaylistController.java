package com.trianasalesianos.dam.Trianafy.controller;

<<<<<<< HEAD
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
=======
import com.trianasalesianos.dam.Trianafy.dto.CreatePlaylistDto;
import com.trianasalesianos.dam.Trianafy.dto.PlaylistDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
>>>>>>> main
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

import java.util.List;
=======
>>>>>>> main

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;
<<<<<<< HEAD
    private final SongRepository songRepository;
=======
    private final PlaylistDtoConverter playlistDto;
>>>>>>> main

    /**
     * Borrar playlist por ID
     *
     * @param id
     * @return codigo de estado 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent()
                .build();

    }

    /**
     * Crea la Playlist sin canciones
     *
     * @param pld
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto pld) {

        if (pld.getName() == null || pld.getDesc() == null) {

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(playlistDto
                        .createPlaylistDtoToPlaylist(pld)));


    }


    @PutMapping("/{id}")
    public ResponseEntity<Playlist> edit(@RequestBody CreatePlaylistDto pld, @PathVariable Long id) {

        return ResponseEntity.of(repository.findById(id).map(
                x -> {
                    x.setName(pld.getName());
                    x.setDescription(pld.getName());
                    repository.save(x);
                    return x;
                })
        );


    }




}
