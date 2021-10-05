package com.trianasalesianos.dam.Trianafy.dto;

import lombok.Builder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Builder
public class PostPlaylistDto {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Lob
    private String description;
}
