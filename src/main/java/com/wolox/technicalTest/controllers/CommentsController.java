package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.CommentResponseDto;
import com.wolox.technicalTest.services.apiServices.ApiService;
import com.wolox.technicalTest.services.apiServices.implementations.CommentsApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ControllerConstants.COMMENTS_BASE)
@CrossOrigin
public class CommentsController {

    private final CommentsApiService apiService;

    public CommentsController(CommentsApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommentResponseDto>> getComments(@RequestParam Optional<String> name, @RequestParam Optional<Integer> user, @RequestParam Optional<String> email) throws Exception {
        return ResponseEntity.ok().body(apiService.getAllcomments(name, user, email));
    }


}
