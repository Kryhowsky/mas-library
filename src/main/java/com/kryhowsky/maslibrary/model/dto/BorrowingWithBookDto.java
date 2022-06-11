package com.kryhowsky.maslibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingWithBookDto {

    private LocalDateTime dateOfBorrowing;

    private LocalDateTime dateOfReturn;

    private BookDto bookDto;
}
