package com.trianasalesianos.dam.Trianafy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlaylistDto {

    private String name;
    private String desc;
    @ElementCollection
    private List<Long> songsId;


}
