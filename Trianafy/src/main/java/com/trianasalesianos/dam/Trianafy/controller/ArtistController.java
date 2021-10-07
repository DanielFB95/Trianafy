package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepository repository;
    private final SongRepository songsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Artist>  findOne(@PathVariable Long id){

        return ResponseEntity.of(repository.findById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){


        songsRepository.findAll()
                                .stream()
                                .filter( x -> x.getArtist().getId() == id)
                                .findFirst()
                                .get()
                                .setArtist(null);

        repository.deleteById(id);

        return ResponseEntity.noContent()
                             .build();


    }

    @GetMapping("/")
    public ResponseEntity<List<Artist>> findAll(){

        return ResponseEntity.ok()
                            .body(repository.findAll());

    }


    @PostMapping("/")
    public ResponseEntity<Artist> create(@RequestBody Artist n){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(n));

    }

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
