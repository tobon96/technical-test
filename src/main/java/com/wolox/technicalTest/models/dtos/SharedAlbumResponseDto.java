package com.wolox.technicalTest.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SharedAlbumResponseDto {

    private String action;
    private String message;
    private String error;
    private Date timeStamp;
}
