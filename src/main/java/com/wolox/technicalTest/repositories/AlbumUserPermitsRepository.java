package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.AlbumUserPermit;
import com.wolox.technicalTest.models.entities.AlbumsUserPermitCompositeKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumUserPermitsRepository extends CrudRepository<AlbumUserPermit, AlbumsUserPermitCompositeKey> {
}
