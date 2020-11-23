package com.wolox.technicalTest.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumsUserPermitCompositeKey implements Serializable {

    private int albumId;
    private int userId;
}
