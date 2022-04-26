package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
