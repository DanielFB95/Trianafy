package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistRepository repository;



    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id){

        repository.deleteById(id);

        return ResponseEntity.noContent()
                             .build();


    }

    @GetMapping("/")
    ResponseEntity<List<Artist>> findAll(){

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
