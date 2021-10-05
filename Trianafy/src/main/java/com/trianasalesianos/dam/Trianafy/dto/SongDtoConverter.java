package com.trianasalesianos.dam.Trianafy.dto;

import com.trianasalesianos.dam.Trianafy.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongDtoConverter {

    public Song createSongDtoToSong (CreateSongDto c){
        return new Song(
            c.getTitle(),
            c.getAlbum(),
            c.getYear()
        );
    }

    public GetSongDto songToGetSongDto(Song s){

        return GetSongDto.builder()
                .title(s.getTitle())
                .artist(s.getArtist().getName())
                .album(s.getAlbum())
                .year(s.getYear())
                .build();
    }




}
