package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Borrowing;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BorrowingService {

    Page<Borrowing> getPage(Pageable pageable);

    List<BorrowingDto> getOvertimeBorrowing();

    void generateOvertimeReport();

    void checkNumberOfActiveBorrowingsByLibraryCardNumber(String libraryCardNumber);

    void addBorrowing(String libraryCardNumber, String iban);

}
