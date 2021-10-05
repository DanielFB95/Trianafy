package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistController {




    @GetMapping("/{id}")
    public ResponseEntity<Artist>  findOne(@PathVariable("id") Long id){

        return ResponseEntity.of(repository.findById(id).orElse(null));
    }

}
