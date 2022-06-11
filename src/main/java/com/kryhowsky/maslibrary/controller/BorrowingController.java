package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.BorrowingMapper;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import com.kryhowsky.maslibrary.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/borrowings", produces = MediaType.APPLICATION_JSON_VALUE)
public class BorrowingController {

    private final BorrowingMapper borrowingMapper;
    private final BorrowingService borrowingService;

    @GetMapping
    @Operation(description = "Returns page of Borrowings with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<BorrowingDto> getBorrowingPage(@RequestParam int page, @RequestParam int size) {
        return borrowingService.getPage(PageRequest.of(page, size)).map(borrowing -> BorrowingDto.builder()
                .dateOfBorrowing(borrowing.getDateOfBorrowing())
                .dateOfReturn(borrowing.getDateOfReturn())
                .bookAuthor("")
                .bookTitle(borrowing.getBook().getTitle())
                .bookDescription(borrowing.getBook().getDescription())
                .build());
    }

    @GetMapping("/overtime")
    @Operation(description = "Returns list of overtime Borrowings.", security = @SecurityRequirement(name = "bearer-key"))
    public List<BorrowingDto> getOvertimeBorrowing() {
        return borrowingService.getOvertimeBorrowing();
    }

    @GetMapping("/overtime/report")
    @Operation(description = "Generates and sends the report.", security = @SecurityRequirement(name = "bearer-key"))
    public void generateOvertimeBorrowingsReport() {

    }

}
