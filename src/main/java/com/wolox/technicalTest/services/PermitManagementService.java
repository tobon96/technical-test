package com.wolox.technicalTest.services;

import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.models.dtos.SharedAlbumRequestDto;
import com.wolox.technicalTest.models.dtos.SharedAlbumResponseDto;
import com.wolox.technicalTest.models.dtos.UserResponseDto;
import com.wolox.technicalTest.models.entities.*;
import com.wolox.technicalTest.repositories.AlbumRepository;
import com.wolox.technicalTest.repositories.AlbumUserPermitsRepository;
import com.wolox.technicalTest.repositories.PermitRepository;
import com.wolox.technicalTest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PermitManagementService {

    private final ApiService apiService;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final PermitRepository permitRepository;
    private final AlbumUserPermitsRepository albumUserPermitsRepository;

    public PermitManagementService(ApiService apiService, UserRepository userRepository, AlbumRepository albumRepository, PermitRepository permitRepository, AlbumUserPermitsRepository albumUserPermitsRepository) {
        this.apiService = apiService;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.permitRepository = permitRepository;
        this.albumUserPermitsRepository = albumUserPermitsRepository;
    }

    public SharedAlbumResponseDto createPermitForUser(SharedAlbumRequestDto requestDto) {

        SharedAlbumResponseDto responseDto = SharedAlbumResponseDto.builder()
                .timeStamp(new Date())
                .action("User permit creation over album")
                .build();

        Optional<User> user = userRepository.findById(requestDto.getUserToBeSharedId());
        Optional<Album> album = albumRepository.findById(requestDto.getAlbumId());
        Optional<Permit> permit = permitRepository.findByPermit(requestDto.getPermit());

        if(user.isEmpty()) {
            try{
                UserResponseDto newUser = apiService.getUser(requestDto.getUserToBeSharedId());

                if(newUser != null) {
                    userRepository.save(User.builder()
                            .id(requestDto.getUserToBeSharedId())
                            .name(newUser.getName())
                            .email(newUser.getEmail())
                            .build());
                }
            } catch (Exception e) {
                responseDto.setError("Provided user doesn't exist");
                return responseDto;
            }
        }

        if(album.isEmpty()) {
            try{
                AlbumResponseDto newAlbum = apiService.getAlbum(requestDto.getAlbumId());

                if(newAlbum != null) {
                    albumRepository.save(Album.builder()
                            .id(requestDto.getUserToBeSharedId())
                            .title(newAlbum.getTitle())
                            .ownerId(newAlbum.getUserId())
                            .build());
                }
            } catch (Exception e) {
                responseDto.setError("Provided album doesn't exist");
                return responseDto;
            }
        }

        if(permit.isEmpty()) {
            responseDto.setMessage("Provided permit doesn't exist");
            return responseDto;
        }

        if(albumUserPermitsRepository.existsById(AlbumsUserPermitCompositeKey.builder()
                .albumId(requestDto.getAlbumId())
                .userId(requestDto.getUserToBeSharedId())
                .build())){
            responseDto.setError("Existing permit for provided user over provided album. Try updating");
            return responseDto;
        }
        albumUserPermitsRepository.save(AlbumUserPermit.builder()
                .albumId(requestDto.getAlbumId())
                .permitId(permit.get().getId())
                .userId(requestDto.getUserToBeSharedId())
                .build());

        responseDto.setMessage("Permit granted for user " + requestDto.getUserToBeSharedId() + " over album " + requestDto.getAlbumId());
        return responseDto;
    }

    public SharedAlbumResponseDto updatePermitForUser(SharedAlbumRequestDto requestDto) {

        SharedAlbumResponseDto responseDto = SharedAlbumResponseDto.builder()
                .timeStamp(new Date())
                .action("User permit update over album")
                .build();

        Optional<Permit> permit = permitRepository.findByPermit(requestDto.getPermit());

        if(permit.isEmpty()) {
            responseDto.setMessage("Provided permit doesn't exist");
            return responseDto;
        }

        if(albumUserPermitsRepository.existsById(AlbumsUserPermitCompositeKey.builder()
                .albumId(requestDto.getAlbumId())
                .userId(requestDto.getUserToBeSharedId())
                .build())){

            albumUserPermitsRepository.save(AlbumUserPermit.builder()
                    .albumId(requestDto.getAlbumId())
                    .permitId(permit.get().getId())
                    .userId(requestDto.getUserToBeSharedId())
                    .build());

        } else {
            responseDto.setError("No existing permit for provided user over provided album");
            return responseDto;
        }

        responseDto.setMessage("Permit granted for user " + requestDto.getUserToBeSharedId() + " over album " + requestDto.getAlbumId());
        return responseDto;
    }

    public SharedAlbumResponseDto getUsersByPermitOverAlbum(Optional<String> permit, Optional<Integer> album) {

        SharedAlbumResponseDto responseDto = SharedAlbumResponseDto.builder()
                .timeStamp(new Date())
                .action("User permit update over album")
                .build();

        return responseDto;
    }
}
