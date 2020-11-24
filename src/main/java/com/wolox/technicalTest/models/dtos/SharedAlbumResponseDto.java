package com.wolox.technicalTest.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wolox.technicalTest.models.dtos.UserResponse.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SharedAlbumResponseDto {

    private String detail;
    private String action;
    private String error;
    private Date timeStamp;
    private List<UserResponseDto> users;
}
