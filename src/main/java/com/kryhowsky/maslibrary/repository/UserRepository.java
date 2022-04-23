package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Administrator, Long>, RevisionRepository<Administrator, Long, Integer> {

    Optional<Administrator> findByEmail(String email);

}
