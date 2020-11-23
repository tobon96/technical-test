package com.wolox.technicalTest.services;

import com.wolox.technicalTest.constants.UtilConstants;
import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.models.dtos.CommentResponseDto;
import com.wolox.technicalTest.models.dtos.PhotosResponseDto;
import com.wolox.technicalTest.models.dtos.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<PhotosResponseDto> getAllPhotos() throws Exception {

        ResponseEntity<PhotosResponseDto[]> response = restTemplate.getForEntity("/photos", PhotosResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }

    public List<PhotosResponseDto> getUserPhotos(int id) throws Exception {

        return getUserAlbums(id).stream()
                .map(album -> getAlbumPhotos(album.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    public PhotosResponseDto getPhoto(int id) throws Exception {

        ResponseEntity<PhotosResponseDto> response = restTemplate.getForEntity("/photos", PhotosResponseDto.class, request);

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

        ResponseEntity<AlbumResponseDto[]> response = restTemplate.getForEntity("/users/" + id + "/albums", AlbumResponseDto[].class, request);

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

    ///// Comments
    public List<CommentResponseDto> getAllcomments(Optional<String> name, Optional<Integer> user) throws Exception {

        List<CommentResponseDto> response = null;

        if(user.isPresent()) {
            response = getComments(UtilConstants.COMMENTS_EMAIL + getUser(user.get()).getEmail());
        } else {
            response = name.isPresent() ? getComments(UtilConstants.COMMENTS_NAME + name) : getComments(UtilConstants.COMMENTS_BASE);
        }

        return response;
    }

    private List<CommentResponseDto> getComments(String url) throws Exception {

        ResponseEntity<CommentResponseDto[]> response = restTemplate.getForEntity(url, CommentResponseDto[].class, request);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception(response.getStatusCode().toString());
        }

    }
}
