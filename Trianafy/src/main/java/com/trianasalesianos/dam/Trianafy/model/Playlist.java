package com.trianasalesianos.dam.Trianafy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
