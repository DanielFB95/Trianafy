package com.trianasalesianos.dam.Trianafy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetSongDto {

    private String title;
    private String artist;
    private String album;
    private String year;
}
