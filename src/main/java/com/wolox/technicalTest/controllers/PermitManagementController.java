package com.wolox.technicalTest.controllers;

import com.wolox.technicalTest.constants.ControllerConstants;
import com.wolox.technicalTest.models.dtos.SharedAlbumRequestDto;
import com.wolox.technicalTest.models.dtos.SharedAlbumResponseDto;
import com.wolox.technicalTest.services.PermitManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(ControllerConstants.PERMITS_BASE)
@CrossOrigin
public class PermitManagementController {

    private final PermitManagementService permitManagementService;

    public PermitManagementController(PermitManagementService permitManagementService) {
        this.permitManagementService = permitManagementService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SharedAlbumResponseDto> createPermit(@Valid @RequestBody SharedAlbumRequestDto requestDto) {

        SharedAlbumResponseDto responseDto = permitManagementService.createPermitForUser(requestDto);
        if(responseDto.getError() != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SharedAlbumResponseDto> updatePermit(@Valid @RequestBody SharedAlbumRequestDto requestDto) {

        SharedAlbumResponseDto responseDto = permitManagementService.updatePermitForUser(requestDto);
        if(responseDto.getError() != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SharedAlbumResponseDto> getUsersByPermitOverAlbum(@RequestParam Optional<String> permit, @RequestParam Optional<Integer> album) {

        SharedAlbumResponseDto responseDto = permitManagementService.getUsersByPermitOverAlbum(permit, album);
        if(responseDto.getError() != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

    }
}
