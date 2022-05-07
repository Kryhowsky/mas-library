package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {

    Author save(Author author);
    Author update(Author author, Long id);
    void delete(Long id);
    Page<Author> getPage(Pageable pageable);
    Author getAuthorById(Long id);

}
