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
public class BorrowingDto {

    private LocalDateTime dateOfBorrowing;
    private LocalDateTime dateOfReturn;
    private String bookTitle;
    private String bookDescription;
    private String bookAuthor;

}
