package com.wolox.technicalTest.repositories;

import com.wolox.technicalTest.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    @Override
    List<User> findAllById(Iterable<Integer> integers);
}
