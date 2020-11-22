package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.AlbumUserPermit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumUserPermitsRepository extends CrudRepository<AlbumUserPermit, Integer> {
}
