package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
