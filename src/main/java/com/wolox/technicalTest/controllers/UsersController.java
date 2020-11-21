package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.UserResponseDto;
import com.wolox.technicalTest.services.ApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerConstants.USERS_BASE)
@CrossOrigin
public class UsersController {

    private final ApiService apiService;

    public UsersController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserResponseDto>> getUsers() throws Exception {
        return ResponseEntity.ok().body(apiService.getUsers());
    }

    @GetMapping(value = ControllerConstants.ID_BASE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDto> getUser(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(apiService.getUser(id));
    }


}
