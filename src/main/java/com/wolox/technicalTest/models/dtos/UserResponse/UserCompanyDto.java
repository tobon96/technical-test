package com.wolox.technicalTest.models.dtos.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyDto {

    private String name;
    private String catchPhrase;
    private String bs;
}
