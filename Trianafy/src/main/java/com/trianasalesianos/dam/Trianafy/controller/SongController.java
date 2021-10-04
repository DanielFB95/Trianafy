package com.trianasalesianos.dam.Trianafy.controller;

import com.trianasalesianos.dam.Trianafy.model.Song;
import com.trianasalesianos.dam.Trianafy.repository.ArtistRepository;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongRepository songRepository;
    private final SongDtoConverter dtoConverter;
    private final ArtistRepository artistRepository;

    @GetMapping("/")
    public ResponseEntity<List<GetSongDto>> findAll(){

        List<Song> data = songRepository.findAll();

        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            List<GetSongDto> result =
                    data.stream()
                            .map(dtoConverter :: songToGetSongDto)
                            .collect(Collectors.toList());
            return ResponseEntity.ok().body(result);
        }
    }
}
