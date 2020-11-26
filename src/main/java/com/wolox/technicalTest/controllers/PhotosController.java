package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.services.apiServices.implementations.PhotosApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.PHOTOS_BASE)
@CrossOrigin
public class PhotosController {

    private final PhotosApiService apiService;

    public PhotosController(PhotosApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotosResponseDto>> getAllPhotos() throws Exception {
        return ResponseEntity.ok().body(apiService.getAllPhotos());
    }

    @GetMapping(value = ControllerConstants.PHOTOS_USER, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PhotosResponseDto>> getUserPhotos(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getUserPhotos(id));
    }

    @GetMapping(value = ControllerConstants.ID_BASE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PhotosResponseDto> getPhoto(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getPhoto(id));
    }
}
