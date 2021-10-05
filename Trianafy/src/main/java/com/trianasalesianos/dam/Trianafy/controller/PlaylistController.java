package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistRepository repository;


    @GetMapping("/")
    public ResponseEntity <List<Playlist>> findAll(){

        
        return ResponseEntity.of( repository.findAll()
                .getName().getId.getSongs().lenght());

    }


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


}
