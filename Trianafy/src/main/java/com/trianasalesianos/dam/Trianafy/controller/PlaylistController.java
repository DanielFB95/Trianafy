package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.dto.GetPlaylistDto;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.dto.CreatePlaylistDto;
import com.trianasalesianos.dam.Trianafy.dto.PlaylistDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;
    private final SongRepository songRepository;
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


    /**
     * Crea la Playlist sin canciones
     *
     * @param pld
     * @return

    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto pld) {

        if (pld.getName() == null || pld.getDesc() == null) {

            return ResponseEntity.badRequest().build();
        }

        PlaylistDtoConverter playlistDto;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(playlistDto
                        .createPlaylistDtoToPlaylist(pld)));


    }

    */


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

    @GetMapping("/{id}/songs/{id2}")
    public ResponseEntity<Song> findOne(@PathVariable Long id2) {

        return ResponseEntity.of(songRepository.findById(id2));


    }


    /**
     *
     * @return Lista de Playlists
     */
    @GetMapping("/")

    public ResponseEntity<List<GetPlaylistDto>> findAll(){

        List<Playlist> data = repository.findAll();

        if (data.isEmpty()){

            return ResponseEntity.notFound().build();
        }else{

            List<GetPlaylistDto> result = data.stream()
                    .map(playlistDto:: playlistToGetPlaylistDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(result);
        }
    }




}
