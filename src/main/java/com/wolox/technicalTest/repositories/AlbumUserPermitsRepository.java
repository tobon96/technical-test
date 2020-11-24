package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.AlbumUserPermit;
import com.wolox.technicalTest.models.entities.AlbumsUserPermitCompositeKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumUserPermitsRepository extends CrudRepository<AlbumUserPermit, AlbumsUserPermitCompositeKey> {

    List<AlbumUserPermit> findAllByAlbum_IdAndPermit_Id(int albumId, int permitId);

    List<AlbumUserPermit> findAllByAlbum_id(int albumId);

    List<AlbumUserPermit> findAllByPermit_Id(int albumId);
}
