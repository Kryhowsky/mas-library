package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.AuthorMapper;
import com.kryhowsky.maslibrary.model.dto.AuthorDto;
import com.kryhowsky.maslibrary.service.AuthorService;
import com.kryhowsky.maslibrary.validator.group.Create;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

    private final AuthorMapper authorMapper;
    private final AuthorService authorService;

    @PostMapping
    @Validated(Create.class)
    @Operation(description = "Allows to add new Author.")
    public AuthorDto saveAuthor(@RequestBody @Valid AuthorDto author) {
        return authorMapper.toDto(authorService.save(authorMapper.toDao(author)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Return Author by given ID.", security = @SecurityRequirement(name = "bearer-key"))
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorMapper.toDto(authorService.getAuthorById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Authors with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<AuthorDto> getAuthorPage(@RequestParam int page, @RequestParam int size) {
        return authorService.getPage(PageRequest.of(page, size)).map(authorMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update Author specified by ID.")
    public AuthorDto updateAuthor(@RequestBody @Valid AuthorDto author, @PathVariable Long id) {
        return authorMapper.toDto(authorService.update(authorMapper.toDao(author), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete Author specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteAuthorById(@PathVariable Long id) {
        authorService.delete(id);
    }

}
