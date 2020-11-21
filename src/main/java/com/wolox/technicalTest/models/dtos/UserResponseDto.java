package com.wolox.technicalTest.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private int id;
    private String name;
    private String username;
    private String email;

    @JsonProperty("address")
    private UserAddressDto userAddress;
    private String phone;
    private String website;

    @JsonProperty("company")
    private UserCompanyDto userCompanyDto;

}
