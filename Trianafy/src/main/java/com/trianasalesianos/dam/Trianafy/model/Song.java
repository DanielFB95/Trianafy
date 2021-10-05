package com.trianasalesianos.dam.Trianafy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Song {

    @Id
    @GeneratedValue

    private Long Id;

    private String title;
    private Artist artist;
    private String album;
    private String year;

    public Song(String title, String album, String year) {
        this.title = title;
        this.album = album;
        this.year = year;
    }
}
