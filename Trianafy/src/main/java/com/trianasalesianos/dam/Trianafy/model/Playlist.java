package com.trianasalesianos.dam.Trianafy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
    @ElementCollection
    private List<Song> songs;

    public Playlist(String name, String description, List<Song> songs) {
        this.name = name;
        this.description = description;
        this.songs = songs;
    }
}
