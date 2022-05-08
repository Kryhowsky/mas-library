package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.BookMapper;
import com.kryhowsky.maslibrary.model.dto.BookDto;
import com.kryhowsky.maslibrary.service.impl.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookMapper bookMapper;
    private final BookService bookService;

    @GetMapping("/{iban}")
    @Operation(description = "Returns Book by given IBAN.", security = @SecurityRequirement(name = "bearer-key"))
    public BookDto getBookByIban(@PathVariable String iban) {
        return bookMapper.toDto(bookService.getBookById(iban));
    }

    @GetMapping
    @Operation(description = "Returns page of Books with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<BookDto> getPaperBookPage(@RequestParam int page, @RequestParam int size) {
        return bookService.getPage(PageRequest.of(page, size)).map(bookMapper::toDto);
    }

}
