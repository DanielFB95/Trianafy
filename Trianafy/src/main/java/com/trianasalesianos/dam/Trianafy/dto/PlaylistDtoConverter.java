package com.trianasalesianos.dam.Trianafy.dto;

import com.trianasalesianos.dam.Trianafy.model.Playlist;
import com.trianasalesianos.dam.Trianafy.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PlaylistDtoConverter {

    private final SongRepository songRepo;

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
