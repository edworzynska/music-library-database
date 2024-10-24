package com.example.music_library_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Transactional
    public Album createAlbum(String title, Artist artist, Short releaseYear){
        Album album = new Album();
        album.setTitle(title);
        album.setReleaseYear(releaseYear);
        album.setArtist(artist);
        albumRepository.save(album);
        return album;
    }

}
