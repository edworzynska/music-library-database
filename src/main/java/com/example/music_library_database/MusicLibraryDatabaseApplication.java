package com.example.music_library_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class MusicLibraryDatabaseApplication {

	private final ArtistService artistService;
	private final AlbumService albumService;
	private ArtistRepository  artistRepository;

	@Autowired
	public MusicLibraryDatabaseApplication(ArtistService artistService, AlbumService albumService) {
		this.artistService = artistService;
		this.albumService = albumService;
	}
	public static void main(String[] args) {
		SpringApplication.run(MusicLibraryDatabaseApplication.class, args);
	}
	@PostMapping("/create-artist")
	public String createArtist(@RequestParam String name, @RequestParam String genre){
		Artist artist = artistService.createArtist(name, genre);
		return String.format("Artist %s successfully added to the database!", artist.getName());
	}
	@PostMapping("/create-album")
	public String createAlbum(@RequestParam String title, @RequestParam Short releaseYear, @RequestParam Long artistID){
		Artist artist = artistService.getById(artistID);
		Album album = albumService.createAlbum(title, artist, releaseYear);
		return String.format("Album %s successfully added to the database!", album.getTitle());
	}
	@GetMapping("/albums")
	public String displayAlbums(){
		StringBuilder str = new StringBuilder();
		for (Album album : albumService.all()){
			str.append(album.toString()).append("\n");
		}
		return str.isEmpty() ? "No albums in the database!" : str.toString();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<String> missingParameters(MissingServletRequestParameterException e){
		String name = e.getParameterName();
		return new ResponseEntity<>("Error: missing parameter " + name.toUpperCase(), HttpStatus.BAD_REQUEST);
	}
}
