package com.wolox.technicalTest.services.apiServices.implementations;

import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.services.apiServices.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumsApiService extends ApiService {

    public AlbumsApiService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public List<AlbumResponseDto> getAllAlbums() throws Exception {

        ResponseEntity<AlbumResponseDto[]> response = restTemplate.getForEntity("/albums", AlbumResponseDto[].class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }

    public List<AlbumResponseDto> getUserAlbums(int id) throws Exception {

        ResponseEntity<AlbumResponseDto[]> response = restTemplate.getForEntity("/users/" + id + "/albums", AlbumResponseDto[].class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public AlbumResponseDto getAlbum(int id) throws Exception {

        ResponseEntity<AlbumResponseDto> response = restTemplate.getForEntity("/albums/" + id, AlbumResponseDto.class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }
}
