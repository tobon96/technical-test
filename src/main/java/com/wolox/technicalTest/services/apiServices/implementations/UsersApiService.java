package com.wolox.technicalTest.services.apiServices.implementations;

import com.wolox.technicalTest.models.dtos.UserResponse.UserResponseDto;
import com.wolox.technicalTest.services.apiServices.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UsersApiService extends ApiService {

    public UsersApiService(RestTemplate restTemplate) {
        super(restTemplate);
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

        ResponseEntity<UserResponseDto> response = restTemplate.getForEntity("/users/" + id, UserResponseDto.class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        }else {
            throw new Exception(response.getStatusCode().toString());
        }
    }
}
