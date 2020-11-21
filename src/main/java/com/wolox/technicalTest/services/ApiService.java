package com.wolox.technicalTest.services;

import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.models.dtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ApiService {

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate;
    private final HttpEntity<String> request;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        request = new HttpEntity<>(headers);
    }

    ///// Users
    public List<UserResponseDto> getUsers() throws Exception {

        ResponseEntity<UserResponseDto[]> response = restTemplate.getForEntity("/users", UserResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public UserResponseDto getUser(int id) throws Exception {

        ResponseEntity<UserResponseDto> response = restTemplate.getForEntity("/users", UserResponseDto.class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }

    ///// Photos
    public List<PhotosResponseDto> getPhotos() throws Exception {

        ResponseEntity<PhotosResponseDto[]> response = restTemplate.getForEntity("/photos", PhotosResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public PhotosResponseDto getPhoto(int id) throws Exception {

        ResponseEntity<PhotosResponseDto> response = restTemplate.getForEntity("/photos", PhotosResponseDto.class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }

    ///// Albums
    public List<AlbumResponseDto> getAllAlbums() throws Exception {

        ResponseEntity<AlbumResponseDto[]> response = restTemplate.getForEntity("/albums", AlbumResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public List<AlbumResponseDto> getUserAlbums(int id) throws Exception {

        ResponseEntity<AlbumResponseDto[]> response = restTemplate.getForEntity("/user/" + id + "/albums", AlbumResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public AlbumResponseDto getAlbum(int id) throws Exception {

        ResponseEntity<AlbumResponseDto> response = restTemplate.getForEntity("/albums", AlbumResponseDto.class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }

}
