package com.songs.songsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songs.songsservice.dto.SongDto;
import com.songs.songsservice.entity.Song;
import com.songs.songsservice.mapper.SongMapper;
import com.songs.songsservice.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
public class SongControllerTest {
    @MockBean
    private SongService songService;

    @MockBean
    private SongMapper songMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllSongList() throws Exception {
        List<Song> songList = Arrays.asList(new Song(), new Song());
        List<SongDto> songDto = Arrays.asList(new SongDto(), new SongDto());

        when(songService.getAllSong()).thenReturn(songList);
        when(songMapper.toDto(any())).thenReturn(new SongDto());

        mockMvc.perform(get("/songs/all").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetOneSong() throws Exception {
        List<Song> songList = Arrays.asList(new Song(), new Song());
        SongDto songDto = new SongDto();

        when(songService.getSong(1L)).thenReturn(new Song());
        when(songMapper.toDto(any())).thenReturn(new SongDto());

        mockMvc.perform(get("/songs/?id=1").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldNotGetOneSong() throws Exception {
        List<Song> songList = Arrays.asList(new Song(), new Song());
        SongDto songDto = new SongDto();

        when(songService.getSong(1L)).thenReturn(null);
        when(songMapper.toDto(any())).thenReturn(null);

        mockMvc.perform(get("/songs/?id=2").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldFailIfUrlIsWrong() throws Exception {
        List<Song> songList = Arrays.asList(new Song(), new Song());
        SongDto songDto = new SongDto();

        when(songService.getSong(1L)).thenReturn(null);
        when(songMapper.toDto(any())).thenReturn(null);

        mockMvc.perform(get("/song/?id=2").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldDeleteSong() throws Exception {
        Song song = new Song();
        SongDto songDto = new SongDto();

        when(songService.deleteSong(song)).thenReturn(song);
        when(songMapper.toDto(any())).thenReturn(songDto);

        mockMvc.perform(delete("/songs/?ids=2,3").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldPostSong() throws Exception {
        Song song = new Song();
        SongDto songDto = new SongDto();

        when(songService.addSong(any())).thenReturn(song);
        when(songMapper.toDto(any())).thenReturn(new SongDto());

        mockMvc.perform(post("/songs/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
