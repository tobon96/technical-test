package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.services.ApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.PHOTOS_BASE)
@CrossOrigin
public class PhotosController {

    private final ApiService apiService;

    public PhotosController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotosResponseDto>> getPhotos() throws Exception {
        return ResponseEntity.ok().body(apiService.getPhotos());
    }

    @GetMapping(value = ControllerConstants.ID_BASE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotosResponseDto> getUser(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getPhoto(id));
    }
}
