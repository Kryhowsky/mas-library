package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.BookstandMapper;
import com.kryhowsky.maslibrary.model.dto.BookstandDto;
import com.kryhowsky.maslibrary.service.BookstandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/bookstands", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookstandController {

    private final BookstandMapper bookstandMapper;
    private final BookstandService bookstandService;

    @PostMapping
    @Operation(description = "Allows to add new Bookstand.")
    public BookstandDto saveBookstand(@RequestBody @Valid BookstandDto bookstand) {
        return bookstandMapper.toDto(bookstandService.save(bookstandMapper.toDao(bookstand)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Lane by given ID.", security = @SecurityRequirement(name = "bearer-key"))
    public BookstandDto getBookstandById(@PathVariable Long id) {
        return bookstandMapper.toDto(bookstandService.getBookstandById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Bookstand with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<BookstandDto> getBookstandPage(@RequestParam int page, @RequestParam int size) {
        return bookstandService.getPage(PageRequest.of(page, size)).map(bookstandMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update Bookstand specified by ID.")
    public BookstandDto updateBookstand(@RequestBody @Valid BookstandDto bookstand, @PathVariable Long id) {
        return bookstandMapper.toDto(bookstandService.update(bookstandMapper.toDao(bookstand), id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Boosktand specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteBookstandById(@PathVariable Long id) {
        bookstandService.delete(id);
    }

}
