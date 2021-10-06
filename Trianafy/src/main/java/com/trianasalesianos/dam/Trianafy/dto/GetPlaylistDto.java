package com.trianasalesianos.dam.Trianafy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPlaylistDto {

    private Long id;
    private String name;
    private String description;
    private int numberOfSongs;


}
