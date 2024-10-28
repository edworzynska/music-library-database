package com.example.music_library_database;

import com.example.music_library_database.model.Album;
import com.example.music_library_database.model.Artist;
import com.example.music_library_database.repository.ArtistRepository;
import com.example.music_library_database.service.AlbumService;
import com.example.music_library_database.service.ArtistService;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication

@Controller
public class MusicLibraryDatabaseApplication {

	private final ArtistService artistService;
	private final AlbumService albumService;

	@Autowired
	public MusicLibraryDatabaseApplication(ArtistService artistService, AlbumService albumService) {
		this.artistService = artistService;
		this.albumService = albumService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicLibraryDatabaseApplication.class, args);
	}

	@GetMapping("/")
	public String homePage() {
		return "index";
	}

	@GetMapping("/create-artist-form")
	public String createArtistForm() {
		return "create-artist";
	}

	@PostMapping("/create-artist")
	public String createArtist(@RequestParam String name, @RequestParam String genre, Model model) {
		Artist artist = artistService.createArtist(name, genre);
		model.addAttribute("message", String.format("Artist %s successfully added to the database!", artist.getName()));
		return "request-result";
	}

	@GetMapping("/create-album-form")
	public String createAlbumForm() {
		return "create-album";
	}

	@PostMapping("/create-album")
	public String createAlbum(@RequestParam String title, @RequestParam Short releaseYear, @RequestParam Long artistID, Model model) {
		Artist artist = artistService.getById(artistID);
		Album album = albumService.createAlbum(title, artist, releaseYear);
		model.addAttribute("message", String.format("Album %s successfully added to the database!", album.getTitle()));
		return "request-result";
	}

	@GetMapping("/albums")
	public String displayAlbums(Model model) {
		model.addAttribute("albums", albumService.all());
		return "albums";
	}
	@GetMapping("artists")
	public String displayArtists(Model model) {
		model.addAttribute("artists", artistService.all());
		return "artists";
	}
	@GetMapping("artists/{id}")
	public String getArtist(@PathVariable Long id, Model model){

		model.addAttribute("artist", artistService.getById(id));
		model.addAttribute("album", albumService.findAllByArtistId(id));
		return "artist-details";
	}
	@GetMapping("albums/{id}")
	public String getAlbum(@PathVariable Long id, Model model){
		Album album = albumService.getById(id);
		Artist artist = albumService.findArtistByAlbumId(album.getId());
		model.addAttribute("album", album);
		model.addAttribute("artist", artist);
		return "album-details";
	}
}