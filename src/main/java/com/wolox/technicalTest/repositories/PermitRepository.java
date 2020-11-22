package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.Permit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitRepository extends CrudRepository<Permit, Integer> {
}
