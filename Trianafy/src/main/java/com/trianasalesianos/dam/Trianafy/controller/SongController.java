package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.dto.GetSongDto;
import com.trianasalesianos.dam.Trianafy.dto.SongDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Song", description = "Controller of songs.")
public class SongController {


    private final SongRepository songRepository;
    private final SongDtoConverter dtoConverter;

    @Operation(summary = "Obtiene una lista de canciones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una canción y se ha modificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna canción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})})
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
    public ResponseEntity<Song> findOne(@PathVariable Long id) {

        return ResponseEntity.of(songRepository.findById(id));


    }

    @Operation(summary = "Modifica una canción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado una canción y se ha modificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna canción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<Song> edit(@RequestBody CreateSongDto s, @PathVariable Long id) {


        return ResponseEntity.of(songRepository.findById(id).map(
                x -> {
                    x.setYear(s.getYear());
                    x.setTitle(s.getTitle());
                    x.setAlbum(s.getAlbum());
                    songRepository.save(x);
                    return x;
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        songRepository.deleteById(id);

        return ResponseEntity.noContent()
                .build();


    }

}
