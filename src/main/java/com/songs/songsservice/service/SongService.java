package com.songs.songsservice.service;

import com.songs.songsservice.entity.Song;

import java.util.List;

public interface SongService {
    Song addSong(Song song);

    Song getSong(Long id);

    Song deleteSong(Song song);

    List<Song> getAllSong();
}
