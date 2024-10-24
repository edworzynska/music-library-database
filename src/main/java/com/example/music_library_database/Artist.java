package com.example.music_library_database;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@EqualsAndHashCode
@Entity
@Table(name="Artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist", cascade = CascadeType.MERGE)
    Set<Album> albums;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "genre")
    private String genre;

    public Artist() {
    }

    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
}
