package com.trianasalesianos.dam.Trianafy.dto;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistDtoConverter {


    public Playlist createPlaylistDtoToPlaylist(CreatePlaylistDto cpl){

        return new Playlist(cpl.getName(),
                            cpl.getDesc(),
                            null);

    }


    public PostPlaylistDto playlistToPostPlaylistDto(Playlist pl){

        return PostPlaylistDto.builder()
                .name(pl.getName())
                .description(pl.getDescription()).build();

    }



}
