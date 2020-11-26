package com.wolox.technicalTest.services.apiServices.implementations;

import com.wolox.technicalTest.constants.UtilConstants;
import com.wolox.technicalTest.exceptions.IncorrectQueryParamsException;
import com.wolox.technicalTest.models.dtos.CommentResponseDto;
import com.wolox.technicalTest.services.apiServices.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsApiService extends ApiService {

    private final UsersApiService usersApiService;

    public CommentsApiService(RestTemplate restTemplate, UsersApiService usersApiService) {
        super(restTemplate);
        this.usersApiService = usersApiService;
    }

    public List<CommentResponseDto> getAllcomments(Optional<String> name, Optional<Integer> user, Optional<String> email) throws Exception {

        if((name.isPresent() && user.isPresent() && email.isPresent()) ||
                (name.isPresent() && user.isPresent()) ||
                (name.isPresent() && email.isPresent()) ||
                (user.isPresent() && email.isPresent())
        ){
            throw new IncorrectQueryParamsException("Incorrect query strings. Must only query by one (1) parameter");
        }

        List<CommentResponseDto> response;
        if(user.isPresent()) {
            response = getComments(UtilConstants.COMMENTS_EMAIL + usersApiService.getUser(user.get()).getEmail());
        } else if(name.isPresent()) {
            response = getComments(UtilConstants.COMMENTS_NAME + name.get());
        } else if(email.isPresent()) {
            response = getComments(UtilConstants.COMMENTS_EMAIL + email.get());
        } else {
            response = getComments(UtilConstants.COMMENTS_BASE);
        }

        return response;
    }

    private List<CommentResponseDto> getComments(String url) throws Exception {

        ResponseEntity<CommentResponseDto[]> response = restTemplate.getForEntity(url, CommentResponseDto[].class, request);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        }else {
            throw new Exception("There was an error retrieving the comments");
        }

    }
}
