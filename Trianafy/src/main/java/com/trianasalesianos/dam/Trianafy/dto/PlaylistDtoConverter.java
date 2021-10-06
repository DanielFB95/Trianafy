package com.trianasalesianos.dam.Trianafy.dto;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import org.springframework.stereotype.Component;


@Component

public class PlaylistDtoConverter {

    public Playlist createPlaylistDtoToPlaylist(CreatePlaylistDto playlist) {
        return new Playlist(
                playlist.getName(),
                playlist.getDescription()
        );



    }

    public GetPlaylistDto playlistToGetPlaylistDto(Playlist p) {


        return GetPlaylistDto
                .builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .numberOfSongs(p.getSongs().size())
                .build();


    }
}
