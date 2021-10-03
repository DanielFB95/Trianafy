package com.trianasalesianos.dam.Trianafy.repository;

import com.trianasalesianos.dam.Trianafy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
