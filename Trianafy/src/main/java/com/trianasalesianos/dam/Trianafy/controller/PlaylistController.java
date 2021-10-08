package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.dto.*;
import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;
    private final SongRepository songRepository;
    private final PlaylistDtoConverter playlistDto;
    private final SongDtoConverter songDto;

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
     *
     * @param pld
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<Playlist> create(@RequestBody CreatePlaylistDto pld) {

        if (pld.getName() == null || pld.getDescription() == null) {

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

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findOnePl(@PathVariable Long id) {

        return ResponseEntity.of(repository.findById(id));


    }



    @GetMapping("/{id}/songs/{id2}")
    public ResponseEntity<GetSongDto> findOneSong(@PathVariable Long id, @PathVariable Long id2) {
        if(repository.getById(id) == null || songRepository.getById(id2) == null){
            return ResponseEntity.badRequest().build();
        }

        if(!repository.getById(id).getSongs().stream().allMatch(x -> x.getId() == id2)){

            return ResponseEntity.notFound().build();
        }

       GetSongDto dto = songDto.songToGetSongDto(songRepository.getById(id2));

        return ResponseEntity.ok().body(dto);


    }


    /**
     *
     * @return Lista de Playlists
     */

    @Operation(summary = "Muestra todas las playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No hay ninguna playlist",
                    content = @Content),
    })
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

    @Operation(summary = "Añade una cancion existente a una playlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha añadido la canción a la playlist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Playlist.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna playlist o canción",
                    content = @Content)
    })

    @PostMapping("/{id1}/songs/{id2}")
    public ResponseEntity<Playlist> addSong(@RequestBody Playlist playlist, @PathVariable Long id1,
                                                @PathVariable Long id2) {

        if ((repository.getById(id1) == null) || (songRepository.getById(id2) == null)){
            return ResponseEntity.badRequest().build();
        }else {

            Playlist pl = repository.getById(id1);

            Song newSong = songRepository.getById(id2);

            newSong.setPlaylist(pl);

            playlist.getSongs().add(newSong);
            repository.save(pl);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(pl);

        }

    }

    @DeleteMapping("{id}/songs/{id2}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id, @PathVariable Long id2){

        if(repository.findById(id).isEmpty() || songRepository.findById(id2).isEmpty()){
            return ResponseEntity
                    .notFound()
                    .build();
        }

        Playlist pl = repository.getById(id);

        Song song = songRepository.getById(id2);

        pl.getSongs().remove(song);

        song.setPlaylist(null);
        repository.save(pl);

        return ResponseEntity
                .noContent()
                .build();

    }



}
