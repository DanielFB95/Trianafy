package com.trianasalesianos.dam.Trianafy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Playlist playlist;

    private String album;
    private String year;

    public Song(String title, String album, String year) {
        this.title = title;
        this.album = album;
        this.year = year;
    }

    public Song(String title, Artist artist, String album, String year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }
}
