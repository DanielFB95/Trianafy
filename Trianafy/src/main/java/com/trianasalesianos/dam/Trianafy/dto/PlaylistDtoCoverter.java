package com.trianasalesianos.dam.Trianafy.dto;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.model.Song;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class PlaylistDtoCoverter {

    public Playlist createPlaylistDtoToPlaylist(CreatePlaylistDto playlist) {
        return new Playlist(
                playlist.getName(),
                playlist.getDescription(),
                null);



    }

    public GetPlaylistDto playlistToGetPlaylistDto(Playlist p) {


        return GetPlaylistDto
                .builder()
                .name(p.getName())
                .description(p.getDescription())
                .numberOfSongs(p.getSongs().stream().filter().count());


    }
}
