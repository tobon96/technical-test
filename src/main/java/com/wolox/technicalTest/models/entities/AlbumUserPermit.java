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
@Table(name = "ALBUMS_USERS_PERMITS")
public class AlbumUserPermit {

    @Id
    private int id;

    @Column(name = "album_id")
    private int albumId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", insertable = false, updatable = false)
    private Album album;

    @Column(name = "permit")
    private int permitId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "permit", insertable = false, updatable = false)
    private Permit permit;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", insertable = false, updatable = false)
    private User user;
}
