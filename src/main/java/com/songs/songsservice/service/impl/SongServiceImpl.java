package com.songs.songsservice.service.impl;

import com.songs.songsservice.entity.Song;
import com.songs.songsservice.repository.SongRepository;
import com.songs.songsservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song getSong(Long id) {
        return songRepository.getReferenceById(id);
    }

    @Override
    public Song deleteSong(Song song) {
         songRepository.delete(song);
         return song;
    }
}
