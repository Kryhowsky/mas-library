package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.model.dao.Genre;
import com.kryhowsky.maslibrary.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/genres", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    @Operation(description = "Allows to get all Genres.")
    public List<Genre> getAllGenres() {
        return genreService.getGenres();
    }

}
