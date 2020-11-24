package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.Permit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermitRepository extends CrudRepository<Permit, Integer> {

    Optional<Permit> findByPermit(String permit);
}
