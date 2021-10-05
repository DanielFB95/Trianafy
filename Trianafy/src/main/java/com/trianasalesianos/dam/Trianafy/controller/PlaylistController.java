package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.dto.CreatePlaylistDto;
import com.trianasalesianos.dam.Trianafy.dto.PlaylistDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;
    private final PlaylistDtoConverter playlistDto;

    /**
     * Borrar playlist por ID
     * @param id
     * @return codigo de estado 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        repository.deleteById(id);
        return ResponseEntity.noContent()
                .build();

    }

    /**
     * Crea la Playlist sin canciones
     * @param pld
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto pld){

        if(pld.getName() == null || pld.getDesc() == null){

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(playlistDto
                        .createPlaylistDtoToPlaylist(pld)));




    }




}
