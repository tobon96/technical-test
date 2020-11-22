package com.wolox.technicalTest.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ALBUMS")
public class Album {

    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "owner")
    private int ownerId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", insertable = false, updatable = false)
    private User owner;
}
