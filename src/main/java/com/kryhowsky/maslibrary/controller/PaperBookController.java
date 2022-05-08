package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.PaperBookMapper;
import com.kryhowsky.maslibrary.model.dto.PaperBookDto;
import com.kryhowsky.maslibrary.service.PaperBookService;
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
@RequestMapping(value = "/api/paper-books", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaperBookController {

    private final PaperBookMapper paperBookMapper;
    private final PaperBookService paperBookService;

    @PostMapping
    @Operation(description = "Allows to add new PaperBook.")
    public PaperBookDto savePaperBook(@RequestBody @Valid PaperBookDto paperBook) {
        return paperBookMapper.toDto(paperBookService.save(paperBookMapper.toDao(paperBook)));
    }

    @GetMapping("/{iban}")
    @Operation(description = "Returns Paperbook by given IBAN.", security = @SecurityRequirement(name = "bearer-key"))
    public PaperBookDto getPaperBookByIban(@PathVariable String iban) {
        return paperBookMapper.toDto(paperBookService.getPaperBookById(iban));
    }

    @GetMapping
    @Operation(description = "Returns page of Paperbooks with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<PaperBookDto> getPaperBookPage(@RequestParam int page, @RequestParam int size) {
        return paperBookService.getPage(PageRequest.of(page, size)).map(paperBookMapper::toDto);
    }

    @PutMapping("/{iban}")
    @Operation(description = "Allows to update Paperbook specified by IBAN.")
    public PaperBookDto updatePaperBook(@RequestBody @Valid PaperBookDto paperBook, @PathVariable String iban) {
        return paperBookMapper.toDto(paperBookService.update(paperBookMapper.toDao(paperBook), iban));
    }

    @DeleteMapping("/{iban}")
    @Operation(description = "Allows to delete Paperbook specified by IBAN.", security = @SecurityRequirement(name = "bearer-key"))
    public void deletePaperBookByIban(@PathVariable String iban) {
        paperBookService.delete(iban);
    }

}
