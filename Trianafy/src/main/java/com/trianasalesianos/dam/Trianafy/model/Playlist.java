package com.trianasalesianos.dam.Trianafy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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


    @ElementCollection
    private List<Song> songs;

    public Playlist(String name, String description, List<Song> songs) {
        this.name = name;
        this.description = description;
        this.songs = songs;
    }
}
