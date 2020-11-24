package com.wolox.technicalTest.models.dtos.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGeoDto {

    private String lat;
    private String lng;
}
