package com.tma.musicManagerment.testController;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tma.musicManagement.controller.MusicController;
import com.tma.musicManagement.dao.MusicDAO;
import com.tma.musicManagement.model.Genre;
import com.tma.musicManagement.model.Music;
import com.tma.musicManagement.model.Musician;
import com.tma.musicManagement.model.Singer;
import com.tma.musicManagement.repository.MusicRepository;
import com.tma.musicManagement.service.impl.GenreServiceImpl;
import com.tma.musicManagement.service.impl.MusicServiceImpl;
import com.tma.musicManagement.service.impl.MusicianServiceImpl;
import com.tma.musicManagement.service.impl.SingerServiceImpl;
import com.tma.musicManagement.utils.Helper;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestMusicService {

	private MusicController musicController;
	private MusicRepository musicRepository;
	private Music music1;
	private Music music2;

	@Before
	public void initTest() {
		music1 = new Music();
		music1.setName("A");
		music1.setReleaseTime("2002-1-1");
		music1.setGenre(new Genre());
		music1.setMusician(new Musician());
		music1.setSinger(new Singer());

		music2 = new Music();
		music2.setName("B");
		music2.setReleaseTime("2002-1-1");
		music2.setGenre(new Genre());
		music2.setMusician(new Musician());
		music2.setSinger(new Singer());
	}

	@Test
	public void test_musicController_getMusics() {

		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);
		Mockito.when(mockMusicRepository.findAll()).thenReturn(Arrays.asList(music1, music2));
		assertEquals(2, Helper.size(musicController.getMusics()));
	}

	@Test
	public void test_musicController_createMusic() throws URISyntaxException {

		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		Mockito.when(mockMusicRepository.save(music1)).thenReturn(music1);
		MusicServiceImpl musicServiceImpl = new MusicServiceImpl();
		GenreServiceImpl genreServiceImpl = new GenreServiceImpl();
		SingerServiceImpl singerServiceImpl = new SingerServiceImpl();
		MusicianServiceImpl musicianServiceImpl = new MusicianServiceImpl();

		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicServiceImpl.setMusicDAO(musicDAO);
		musicServiceImpl.setGenreService(genreServiceImpl);
		musicServiceImpl.setMusicianService(musicianServiceImpl);
		musicServiceImpl.setSingerService(singerServiceImpl);
		musicController.setMusicService(musicServiceImpl);
		try {
			assertEquals("<406 Not Acceptable,Genre is not valid,{}>", musicController.createMusic(music1).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_musicController_updateMusic_Valid() {

		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Mockito.when(mockMusicRepository.findOne(11)).thenReturn(music1);
		music2.setId(11);
		Mockito.when(mockMusicRepository.save(music1)).thenReturn(music1);
		assertEquals("<204 No Content,{}>", musicController.updateMusic(11, music2).toString());
	}

	@Test
	public void test_musicController_updateMusic_NotValid() {

		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Mockito.when(mockMusicRepository.findOne(9)).thenReturn(null);
		assertEquals("<404 Not Found,{}>", musicController.updateMusic(9, music1).toString());
	}

	@Test
	public void test_musicController_deleteMusic_Valid() {

		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);
		Mockito.when(mockMusicRepository.findOne(10)).thenReturn(music1);
		assertEquals("<204 No Content,{}>", musicController.deleteMusic(10).toString());
	}

	@Test
	public void test_musicController_deleteMusic_NotValid() throws Exception {
		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);
		Mockito.when(mockMusicRepository.findOne(10)).thenReturn(null);
		assertEquals("<404 Not Found,{}>", musicController.deleteMusic(10).toString());
	}

	@Test
	public void test_musicController_getGenreQuantites() throws Exception {
		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Genre genre1 = new Genre();
		genre1.setId(1);
		genre1.setName("Hai");
		Genre genre2 = new Genre();
		genre2.setId(2);
		genre2.setName("Hai2");

		Mockito.when(mockMusicRepository.getGenreQuantities()).thenReturn(null);
		assertEquals(null, musicController.getGenreQuantities());
	}

	@Test
	public void test_musicController_getMusicianQuantites() throws Exception {
		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Genre genre1 = new Genre();
		genre1.setId(1);
		genre1.setName("Hai");
		Genre genre2 = new Genre();
		genre2.setId(2);
		genre2.setName("Hai2");

		Mockito.when(mockMusicRepository.getMusicianQuantities()).thenReturn(null);
		assertEquals(null, musicController.getMusicianQuantities());
	}

	@Test
	public void test_musicController_getSingerQuantites() throws Exception {
		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Mockito.when(mockMusicRepository.getSingerQuantities()).thenReturn(null);
		assertEquals(null, musicController.getSingerQuantities());
	}

	@Test
	public void test_musicController_getMusicsBySid() throws Exception {
		MusicRepository mockMusicRepository = Mockito.mock(MusicRepository.class);
		MusicServiceImpl musicService = new MusicServiceImpl();
		MusicDAO musicDAO = new MusicDAO();
		musicController = new MusicController();
		musicDAO.setMusicRepository(mockMusicRepository);
		musicService.setMusicDAO(musicDAO);
		musicController.setMusicService(musicService);

		Singer singer = new Singer();

		Mockito.when(mockMusicRepository.getMusicsBySinger(singer)).thenReturn(null);
		assertEquals(null, musicController.getMusicsBySid(singer));
	}
}
