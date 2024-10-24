package com.example.music_library_database;

import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MusicLibraryDatabaseApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private MusicLibraryDatabaseApplication musicLibraryDatabaseApplication;

	@BeforeAll
	public static void initTest() throws SQLException {

        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }
    @Transactional
    @BeforeEach
    public void setUp(){

			Artist artist1 = new Artist();
			artist1.setName("Muse");
			artist1.setGenre("Alternative");
			artistRepository.save(artist1);

			Artist artist2 = new Artist();
			artist2.setName("ABBA");
			artist2.setGenre("Pop");
			artistRepository.save(artist2);

			Artist artist3 = new Artist();
			artist3.setName("Fryderyk Chopin");
			artist3.setGenre("Classical");
			artistRepository.save(artist3);

			Artist artist4 = new Artist();
			artist4.setName("Placebo");
			artist4.setGenre("Alternative");
			artistRepository.save(artist4);

		}

    @Test
    void createsArtist() {
		var result = musicLibraryDatabaseApplication.createArtist("Foo Fighters", "Rock");
		assertEquals("Artist Foo Fighters successfully added to the database!", result);
    }

	@Test
	void returnsOkStatusIfCorrectParametersProvided() throws Exception{
		mockMvc.perform(post("/create-artist").param("name", "Foo Fighters").param("genre", "Rock"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().string("Artist Foo Fighters successfully added to the database!"));
	}

	@Test
	void returnsExceptionMessageIfMissingParams() throws Exception {
		mockMvc.perform(post("/create-artist"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().string("Error: missing parameter NAME"));
	}

	@Test
	void createsAlbum() {
		var result = musicLibraryDatabaseApplication.createAlbum("Absolution", (short) 2004, 2L);
		assertEquals("Album Absolution successfully added to the database!", result);
		while(true){}
	}

	@Test
	void returnsExceptionMessageIfMissingParamsInAlbum() throws Exception{
		mockMvc.perform(post("/create-album").param("title", "Absolution").param("releaseYear", "2004"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().string("Error: missing parameter ARTISTID"));
		mockMvc.perform(post("/create-album").param("title", "Absolution").param("artistId", "1"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().string("Error: missing parameter RELEASEYEAR"));
		mockMvc.perform(post("/create-album").param("artistId", "1").param("releaseYear", "2004"))
				.andExpect(status().is4xxClientError())
				.andExpect(content().string("Error: missing parameter TITLE"));

	}
}
