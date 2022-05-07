package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
