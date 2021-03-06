package com.wolox.technicalTest.services;

import com.wolox.technicalTest.models.dtos.AlbumResponseDto;
import com.wolox.technicalTest.models.dtos.SharedAlbumRequestDto;
import com.wolox.technicalTest.models.dtos.SharedAlbumResponseDto;
import com.wolox.technicalTest.models.dtos.UserResponse.UserResponseDto;
import com.wolox.technicalTest.models.entities.*;
import com.wolox.technicalTest.repositories.AlbumRepository;
import com.wolox.technicalTest.repositories.AlbumUserPermitsRepository;
import com.wolox.technicalTest.repositories.PermitRepository;
import com.wolox.technicalTest.repositories.UserRepository;
import com.wolox.technicalTest.services.apiServices.implementations.AlbumsApiService;
import com.wolox.technicalTest.services.apiServices.implementations.UsersApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermitManagementService {

    private final UsersApiService usersApiService;
    private final AlbumsApiService albumsApiService;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final PermitRepository permitRepository;
    private final AlbumUserPermitsRepository albumUserPermitsRepository;

    public PermitManagementService(UsersApiService usersApiService, AlbumsApiService albumsApiService, UserRepository userRepository, AlbumRepository albumRepository, PermitRepository permitRepository, AlbumUserPermitsRepository albumUserPermitsRepository) {
        this.usersApiService = usersApiService;
        this.albumsApiService = albumsApiService;
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
                UserResponseDto newUser = usersApiService.getUser(requestDto.getUserToBeSharedId());

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
                AlbumResponseDto newAlbum = albumsApiService.getAlbum(requestDto.getAlbumId());

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
            responseDto.setError("Provided permit doesn't exist");
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

        responseDto.setAction("Permit granted for user " + requestDto.getUserToBeSharedId() + " over album " + requestDto.getAlbumId());
        return responseDto;
    }

    public SharedAlbumResponseDto updatePermitForUser(SharedAlbumRequestDto requestDto) {

        SharedAlbumResponseDto responseDto = SharedAlbumResponseDto.builder()
                .timeStamp(new Date())
                .action("User permit update over album")
                .build();

        Optional<Permit> permit = permitRepository.findByPermit(requestDto.getPermit());

        if(permit.isEmpty()) {
            responseDto.setError("Provided permit doesn't exist");
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

        responseDto.setDetail("Permit granted for user " + requestDto.getUserToBeSharedId() + " over album " + requestDto.getAlbumId());
        return responseDto;
    }

    public SharedAlbumResponseDto getUsersByPermitOverAlbum(Optional<String> userPermit, Optional<Integer> album) {

        SharedAlbumResponseDto responseDto = SharedAlbumResponseDto.builder()
                .timeStamp(new Date())
                .action("Get users given a permit and an albumId")
                .build();

        if(userPermit.isPresent() && album.isPresent()) {

            Optional<Permit> permit = permitRepository.findByPermit(userPermit.get());
            if(permit.isEmpty()) {
                responseDto.setError("The provided permit does not exist");
                return null;
            }

            List<AlbumUserPermit> usersPermits = albumUserPermitsRepository.findAllByAlbum_IdAndPermit_Id(album.get(), permit.get().getId());

            responseDto.setUsers(userRepository.findAllById(usersPermits.stream()
                    .map(AlbumUserPermit::getUserId)
                    .collect(Collectors.toList()))
                    .stream().map(user -> {
                        try {
                            return usersApiService.getUser(user.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).collect(Collectors.toList()));

            responseDto.setDetail("Users successfully retrieved for album " + album + " with permit " + permit.get().getPermit());
        }

        return responseDto;
    }
}
