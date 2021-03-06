package com.tma.musicManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tma.musicManagement.model.Playlist;
import com.tma.musicManagement.service.PlaylistService;

@CrossOrigin
@RestController
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	@GetMapping(path = "/playlist")
	public @ResponseBody List<?> getPlaylistByUId(@RequestParam int uid) {
		return playlistService.getPlaylistByUId(uid);
	}

	@GetMapping(path = "/playlists")
	public @ResponseBody Iterable<Playlist> getPlaylists() {
		return playlistService.getPlaylists();
	}

	@PostMapping(path = "/playlist")
	public ResponseEntity<Object> createPlaylist(@RequestBody Playlist playlist) {
		return playlistService.createPlaylist(playlist);
	}

	@DeleteMapping(path = "/playlist")
	public void deletePlaylistByTwoId(@RequestParam int uid, @RequestParam int mid) {
		playlistService.deletePlaylistByID(uid, mid);
	}

}
