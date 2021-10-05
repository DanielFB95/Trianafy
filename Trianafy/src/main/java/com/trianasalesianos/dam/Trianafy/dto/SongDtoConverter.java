package com.trianasalesianos.dam.Trianafy.dto;

import org.springframework.stereotype.Component;

@Component
public class SongDtoConverter {

    public Song createSongDtoToSong(CreateSongDto s) {
        return new Song(
                s.getTitle(),
                s.getAlbum(),
                s.getYear()

        );
    }

    public GetSongDto songToGetSongDto(Song s) {


        GetSongDto result = new GetSongDto();

        result.setTitle(s.getTitle());
        result.setAlbum(s.getAlbum());
        result.setArtist(s.getArtist().getName());

        return result;



    }


}
