package com.wolox.technicalTest.models.dtos.UserResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDto {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @JsonProperty("geo")
    private UserGeoDto userGeoDto;
}
