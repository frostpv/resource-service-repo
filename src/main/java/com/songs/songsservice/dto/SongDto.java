package com.songs.songsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private long id;
    private String name;
    private String artist;
    private String album;
    private String length;
    private int resourceId;
    private int year;
}
