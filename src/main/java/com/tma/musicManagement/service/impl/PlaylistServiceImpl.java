package com.tma.musicManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tma.musicManagement.model.Playlist;
import com.tma.musicManagement.repository.PlaylistRepository;
import com.tma.musicManagement.service.PlaylistService;

@Service
@Primary
public class PlaylistServiceImpl implements PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;

	public void setPlaylistRepository(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}

	@Override
	public List<?> getPlaylistByUId(int id) {
		return (List<?>) playlistRepository.getMusicsByUId(id);
	}

	@Override
	public ResponseEntity<Object> createPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
		return ResponseEntity.noContent().build();
	}

	@Override
	public Iterable<Playlist> getPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public void deletePlaylistByID(int uid, int mid) {
		playlistRepository.deleteByTwoID(uid, mid);
	}

	@Override
	public void deletePlaylist(Playlist playlist) {
		playlistRepository.delete(playlist);
	}

}