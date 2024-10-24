package com.example.music_library_database.service;

import com.example.music_library_database.model.Album;
import com.example.music_library_database.repository.AlbumRepository;
import com.example.music_library_database.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public List<Album> all(){
        return albumRepository.findAll();
    }
    public Album getById(Long id){
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()){
            return album.get();
        }
        else throw new RuntimeException("No matching artist found in the database!");
    }
    public List<Album> findAllByArtistId(Long artistId){
        return albumRepository.findByArtistId(artistId);
    }
}
