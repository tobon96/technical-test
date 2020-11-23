package com.wolox.technicalTest.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SharedAlbumRequestDto {

    @NotNull(message = "The album id cannot be null")
    private Integer albumId;

    @NotNull(message = "The id of the user receiving permission cannot be null")
    private Integer userToBeSharedId;

    @NotNull(message = "The permit over the album cannot be null")
    @NotEmpty(message = "The permit over the album cannot be empty")
    private String permit;
}
