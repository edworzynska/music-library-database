package com.example.music_library_database.repository;

import com.example.music_library_database.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository <Artist, Long> {
}
