package com.songs.songsservice.mapper;

import com.songs.songsservice.dto.SongDto;
import com.songs.songsservice.entity.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDto toDto(Song song);
    Song toEntity(SongDto songDto);
}
