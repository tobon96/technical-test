package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.services.ApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.USERS_BASE)
@CrossOrigin
public class AlbumsController {

    private final ApiService apiService;

    public AlbumsController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlbumResponseDto>> getAlbums() throws Exception {
        return ResponseEntity.ok().body(apiService.getAllAlbums());
    }

    @GetMapping(value = ControllerConstants.ALBUMS_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlbumResponseDto>> getUserAlbums(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getUserAlbums(id));
    }

    @GetMapping(value = ControllerConstants.ID_BASE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AlbumResponseDto> getAlbum(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getAlbum(id));
    }
}
