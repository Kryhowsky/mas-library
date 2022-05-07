package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>, RevisionRepository<Administrator, Long, Integer> {
}
