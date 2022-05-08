package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.EbookMapper;
import com.kryhowsky.maslibrary.model.dto.EbookDto;
import com.kryhowsky.maslibrary.service.EbookService;
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
@RequestMapping(value = "/api/ebooks", produces = MediaType.APPLICATION_JSON_VALUE)
public class EbookController {

    private final EbookMapper ebookMapper;
    private final EbookService ebookService;

    @PostMapping
    @Operation(description = "Allows to add new Ebook.")
    public EbookDto saveEbook(@RequestBody @Valid EbookDto ebook) {
        return ebookMapper.toDto(ebookService.save(ebookMapper.toDao(ebook)));
    }

    @GetMapping("/{iban}")
    @Operation(description = "Returns Ebook by given IBAN.", security = @SecurityRequirement(name = "bearer-key"))
    public EbookDto getEbookByIban(@PathVariable String iban) {
        return ebookMapper.toDto(ebookService.getEbookById(iban));
    }

    @GetMapping
    @Operation(description = "Returns page of Ebooks with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<EbookDto> getEbookPage(@RequestParam int page, @RequestParam int size) {
        return ebookService.getPage(PageRequest.of(page, size)).map(ebookMapper::toDto);
    }

    @PutMapping("/{iban}")
    @Operation(description = "Allows to update Ebook specified by IBAN.")
    public EbookDto updateEbook(@RequestBody @Valid EbookDto ebook, @PathVariable String iban) {
        return ebookMapper.toDto(ebookService.update(ebookMapper.toDao(ebook), iban));
    }

    @DeleteMapping("/{iban}")
    @Operation(description = "Allows to delete Ebook specified by IBAN.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteEbookByIban(@PathVariable String iban) {
        ebookService.delete(iban);
    }

}
