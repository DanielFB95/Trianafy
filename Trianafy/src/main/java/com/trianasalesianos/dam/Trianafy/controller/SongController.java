package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.dto.CreateSongDto;
import com.trianasalesianos.dam.Trianafy.dto.GetSongDto;
import com.trianasalesianos.dam.Trianafy.dto.SongDtoConverter;
import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@Tag(name = "Song", description = "Controller of songs.")
public class SongController {


    private final SongRepository songRepository;
    private final SongDtoConverter dtoConverter;
    private final ArtistRepository artistRepository;

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


    @Operation(summary = "Muestra una canción.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la canción.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna canción.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetSongDto> findOne(@PathVariable Long id) {

        GetSongDto dto = dtoConverter.songToGetSongDto(songRepository.getById(id));

        return ResponseEntity.ok().body(dto);
    }


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


        return ResponseEntity.of(songRepository.findById(id).map(c -> {
                    c.setAlbum(s.getAlbum());
                    c.setTitle(s.getTitle());
                    c.setYear(s.getYear());
                    songRepository.save(c);
                    return c;
                })
        );


    }



    @Operation(summary = "Crea una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha creado y guardado la canción",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha guardado la canción debido a un error",
                    content = @Content)
    })


    @PostMapping("/")
    public ResponseEntity<Song> create(@RequestBody CreateSongDto newSong) {



        Song s = dtoConverter.createSongDtoToSong(newSong);

        Artist a = artistRepository.findById(newSong.getArtistId()).orElse(null);

        s.setArtist(a);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(songRepository.save(s));
    }


    @Operation(summary = "Elimina una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Borrado realizado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})
    })


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        songRepository.deleteById(id);

        return ResponseEntity.noContent()
                .build();


    }

}
