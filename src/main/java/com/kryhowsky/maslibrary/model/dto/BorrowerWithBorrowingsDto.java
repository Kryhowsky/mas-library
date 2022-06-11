package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.model.dao.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerWithBorrowingsDto {

    private String firstName;
    private String lastName;
    private String email;
    private Sex sex;
    private String address;
    private String libraryCardNumber;
    private List<BorrowingDto> borrowings;
    private BorrowerDto borrower;

}
