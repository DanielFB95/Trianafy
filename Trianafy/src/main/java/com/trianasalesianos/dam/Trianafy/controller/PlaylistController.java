package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;
    private final SongRepository songRepository;

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

    @DeleteMapping("{id}/songs/{id2}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id, @PathVariable Long id2){

        if(repository.findById(id).isEmpty() || songRepository.findById(id2).isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }
            List<Song> songs = repository.getById(id).getSongs();
            songs.remove(songs.stream().filter( s -> s.getId() == id2));
            repository.getById(id).setSongs(songs);
            return ResponseEntity
                    .noContent()
                    .build();

    }


}
