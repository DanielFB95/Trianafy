package com.trianasalesianos.dam.Trianafy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSongDto {

    private Long artistId;

    private String title;
    private String album;
    private int year;




}
