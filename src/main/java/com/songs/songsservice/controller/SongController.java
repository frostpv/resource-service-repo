package com.songs.songsservice.controller;

import com.songs.songsservice.dto.SongDto;
import com.songs.songsservice.entity.Song;
import com.songs.songsservice.mapper.SongMapper;
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

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/songs")
public class SongController {


    @Autowired
    SongService songService;

    @Autowired
    SongMapper songMapper;

    @PostMapping
    public @ResponseBody ResponseEntity<SongDto> addNewSong(@RequestBody SongDto songDto) {
        Song song = songService.addSong(songMapper.toEntity(songDto));

        return new ResponseEntity<>(songMapper.toDto(song), HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody ResponseEntity<SongDto> getSong(@RequestParam String id) {
        Long aLong = Long.valueOf(id);
        Song song = songService.getSong(aLong);

        return new ResponseEntity<>(songMapper.toDto(song), HttpStatus.OK);
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<SongDto>> getAllSong() {
        List<SongDto> songDtos = songService.getAllSong().stream()
                .map(song -> songMapper.toDto(song))
                .collect(Collectors.toList());

        return new ResponseEntity<>(songDtos, HttpStatus.OK);
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity<List<SongDto>> deleteSongs(@RequestParam List<String> ids) {
        List<SongDto> songs = ids.stream()
                .map(s -> songService.getSong(Long.valueOf(s)))
                .map(song -> songService.deleteSong(song))
                .map(song -> songMapper.toDto(song))
                .collect(Collectors.toList());

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
