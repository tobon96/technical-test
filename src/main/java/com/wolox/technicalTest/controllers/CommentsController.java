package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.models.dtos.CommentResponseDto;
import com.wolox.technicalTest.services.ApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerConstants.COMMENTS_BASE)
@CrossOrigin
public class CommentsController {

    private final ApiService apiService;

    public CommentsController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentResponseDto>> getComments(@RequestParam Optional<String> name, @RequestParam Optional<Integer> user) throws Exception {
        return ResponseEntity.ok().body(apiService.getAllcomments(name, user));
    }


}
