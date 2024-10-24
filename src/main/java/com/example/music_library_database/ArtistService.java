package com.example.music_library_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    public Artist createArtist(String name, String genre){
        Artist artist = new Artist();
        artist.setName(name);
        artist.setGenre(genre);
        artistRepository.save(artist);
        return artist;
    }

    public Artist getById(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()){
            return artist.get();
        }
        else throw new RuntimeException("No matching artist found in the database!");
    }
}
