package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}
