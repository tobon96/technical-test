package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {
}
