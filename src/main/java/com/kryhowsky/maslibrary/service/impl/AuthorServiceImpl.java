package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Author;
import com.kryhowsky.maslibrary.repository.AuthorRepository;
import com.kryhowsky.maslibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author author) {
        var result = authorRepository.save(author);
        return result;
    }

    @Override
    @Transactional
    public Author update(Author author, Long id) {

        var authorDb = getAuthorById(id);
        authorDb.setFirstName(author.getFirstName());
        authorDb.setLastName(author.getLastName());
        authorDb.setPseudonym(author.getPseudonym());

        return authorDb;
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Page<Author> getPage(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getById(id);
    }

}
