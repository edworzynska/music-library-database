package com.example.music_library_database.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Entity
@Table(name = "Albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Getter
    @Setter
    @Column(name = "title")
    private String title;

    @Getter
    @Setter
    @Column(name = "release_year")
    private Short releaseYear;

    public Album() {
    }

    public Album(Artist artist, String title, Short releaseYear) {
        this.artist = artist;
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public String toString(){
        return getTitle() + " - " + getArtist().getName();
    }
    public String getRecord(){
        return getReleaseYear() + " - " + getTitle();
    }
}
