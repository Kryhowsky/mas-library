package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Genre;
import com.kryhowsky.maslibrary.repository.GenreRepository;
import com.kryhowsky.maslibrary.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

}
