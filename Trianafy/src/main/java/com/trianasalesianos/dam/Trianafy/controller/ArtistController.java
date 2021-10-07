package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
@Tag(name = "Artist", description = "Controller of artist.")
public class ArtistController {

    private final ArtistRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Artist>  findOne(@PathVariable Long id){

        return ResponseEntity.of(repository.findById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent()
                             .build();


    }

    @GetMapping("/")
    public ResponseEntity<List<Artist>> findAll(){

        return ResponseEntity.ok()
                            .body(repository.findAll());

    }

    @Operation(summary = "Añade un artista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha añadido un artista.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún artista.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})})
    @PostMapping("/")
    public ResponseEntity<Artist> create(@RequestBody Artist n){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(n));

    }

    @Operation(summary = "Modifica un artista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado un artista y se ha modificado.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún artista.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Song.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<Artist> edit(@RequestBody Artist a, @PathVariable Long id){

        return ResponseEntity.of(
                repository.findById(id).map(c ->{
                    c.setName(a.getName());
                    repository.save(c);
                    return c;
                })
        );
    }



}
