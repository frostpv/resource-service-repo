package com.songs.songsservice.controller;

import com.songs.songsservice.converter.SongConverter;
import com.songs.songsservice.dto.SongDto;
import com.songs.songsservice.entity.Song;
import com.songs.songsservice.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public @ResponseBody ResponseEntity<SongDto> addNewSong(@RequestBody SongDto songDto) {
        Song song = songService.addSong(SongConverter.toEntity(songDto));

        return new ResponseEntity<>(SongConverter.toDto(song), HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<SongDto> getSong(@RequestParam String id) {
        Long aLong = Long.valueOf(id);
        Song song = songService.getSong(aLong);

        return new ResponseEntity<>(SongConverter.toDto(song), HttpStatus.OK);
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity<List<SongDto>> deleteSongs(@RequestParam String ids) {
        List<SongDto> songs = Arrays.stream(ids.trim().split(","))
                .map(s -> songService.getSong(Long.valueOf(s)))
                .map(song -> songService.deleteSong(song))
                .map(SongConverter::toDto)
                .toList();

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
