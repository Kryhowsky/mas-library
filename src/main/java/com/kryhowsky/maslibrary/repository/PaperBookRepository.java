package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.PaperBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperBookRepository extends JpaRepository<PaperBook, String> {
}
