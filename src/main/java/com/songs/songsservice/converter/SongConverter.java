package com.songs.songsservice.converter;

import com.songs.songsservice.dto.SongDto;
import com.songs.songsservice.entity.Song;

public class SongConverter {

    public static SongDto toDto(Song song) {
        SongDto dto = new SongDto();
        dto.setId(song.getId());
        dto.setName(song.getName());
        dto.setArtist(song.getArtist());
        dto.setYear(song.getYear());
        dto.setLength(song.getLength());
        dto.setAlbum(song.getAlbum());
        dto.setResourceId(song.getResourceId());

        return dto;
    }

    public static Song toEntity(SongDto dto) {
        Song song = new Song();
        song.setId(dto.getId());
        song.setName(dto.getName());
        song.setArtist(dto.getArtist());
        song.setYear(dto.getYear());
        song.setLength(dto.getLength());
        song.setAlbum(dto.getAlbum());
        song.setResourceId(dto.getResourceId());

        return song;
    }
}
