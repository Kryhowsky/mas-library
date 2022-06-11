package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.BorrowerMapper;
import com.kryhowsky.maslibrary.model.dto.BorrowerDto;
import com.kryhowsky.maslibrary.model.dto.BorrowerWithBorrowingsDto;
import com.kryhowsky.maslibrary.service.BorrowerService;
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
@RequestMapping(value = "api/borrowers", produces = MediaType.APPLICATION_JSON_VALUE)
public class BorrowerController {

    private final BorrowerMapper borrowerMapper;
    private final BorrowerService borrowerService;

    @PostMapping
    @Validated(Create.class)
    @Operation(description = "Allows to add new Borrower.")
    public BorrowerDto saveBorrower(@RequestBody @Valid BorrowerDto borrower) {
        return borrowerMapper.toDto(borrowerService.save(borrowerMapper.toDao(borrower)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns borrower by given ID.", security = @SecurityRequirement(name = "bearer-key"))
    public BorrowerDto getBorrowerById(@PathVariable Long id) {
        return borrowerMapper.toDto(borrowerService.getBorrowerById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Borrowers with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<BorrowerDto> getBorrowerPage(@RequestParam int page, @RequestParam int size) {
        return borrowerService.getPage(PageRequest.of(page, size)).map(borrowerMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update borrower specified by ID.")
    public BorrowerDto updateBorrower(@RequestBody @Valid BorrowerDto borrower, @PathVariable Long id) {
        return borrowerMapper.toDto(borrowerService.update(borrowerMapper.toDao(borrower), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete Borrower specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteBorrowerById(@PathVariable Long id) {
        borrowerService.delete(id);
    }

    @GetMapping("/{id}/borrowings")
    @Operation(description = "Allows to get Borrower specified by ID with borrowings.", security = @SecurityRequirement(name = "bearer-key"))
    public BorrowerWithBorrowingsDto getBorrowerWithBorrowingsById(@PathVariable Long id) {
        return borrowerService.getBorrowerWithBorrowingsById(id);
    }

}
