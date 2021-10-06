package com.trianasalesianos.dam.Trianafy.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;


    @ElementCollection
    private List<Song> songs;

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;

    }


}
