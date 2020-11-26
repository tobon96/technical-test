package com.wolox.technicalTest.services.apiServices.implementations;

import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.services.apiServices.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotosApiService extends ApiService {

    private final AlbumsApiService albumsApiService;

    public PhotosApiService(RestTemplate restTemplate, AlbumsApiService albumsApiService) {
        super(restTemplate);
        this.albumsApiService = albumsApiService;
    }

    public List<PhotosResponseDto> getAllPhotos() throws Exception {

        ResponseEntity<PhotosResponseDto[]> response = restTemplate.getForEntity("/photos", PhotosResponseDto[].class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public List<PhotosResponseDto> getUserPhotos(int id) throws Exception {

        return albumsApiService.getUserAlbums(id).stream()
                .map(album -> getAlbumPhotos(album.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    public PhotosResponseDto getPhoto(int id) throws Exception {

        ResponseEntity<PhotosResponseDto> response = restTemplate.getForEntity("/photos/" + id, PhotosResponseDto.class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }

    public List<PhotosResponseDto> getAlbumPhotos(int id) {

        ResponseEntity<PhotosResponseDto[]> response = restTemplate.getForEntity("/albums/" + id + "/photos", PhotosResponseDto[].class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            return null;
        }
    }
}
