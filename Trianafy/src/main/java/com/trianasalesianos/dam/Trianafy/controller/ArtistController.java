package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Artist;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepository repository;

    @PostMapping("/")
    public ResponseEntity<Artist> create(@RequestBody Artist n){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repository.save(n));

    }

    @PutMapping("/{id}")
    public ResponseEntity <Artist> edit(@RequestBody Artist a, @PathVariable Long id){

        return ResponseEntity.of(
                repository.findById(id).map(c ->{
                    a.setName(a.getName());
                    repository.save(c);
                    return c;
                })
        );
    }


}
