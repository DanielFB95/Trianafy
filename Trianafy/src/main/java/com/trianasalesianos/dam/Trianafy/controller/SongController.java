package com.trianasalesianos.dam.Trianafy.controller;


import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/song")
@RequiredArgsConstructor
public class SongController {


    private final SongRepository repository;


    /**
     * Buscar cancion por ID
     */
    @GetMapping("/{id}")
    ResponseEntity<Song> findOne(@PathVariable Long id){

        return ResponseEntity.of(repository.findById(id));


    }

}
