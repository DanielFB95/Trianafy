package com.trianasalesianos.dam.Trianafy.dto;


import com.trianasalesianos.dam.Trianafy.model.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreatePlaylistDto {

    private String name;
    private String description;



}
