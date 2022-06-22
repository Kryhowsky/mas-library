package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.error.TooManyActiveBorrowingsException;
import com.kryhowsky.maslibrary.generator.file.impl.CsvFileGenerator;
import com.kryhowsky.maslibrary.model.dao.Borrowing;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import com.kryhowsky.maslibrary.repository.BorrowingRepository;
import com.kryhowsky.maslibrary.service.BorrowerService;
import com.kryhowsky.maslibrary.service.BorrowingService;
import com.kryhowsky.maslibrary.service.PaperBookService;
import com.kryhowsky.maslibrary.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final PersonService personService;
    private final BorrowerService borrowerService;
    private final PaperBookService paperBookService;

    private final CsvFileGenerator csvFileGenerator;
    private final JavaMailSender javaMailSender;


    @Override
    public Page<Borrowing> getPage(Pageable pageable) {
        return borrowingRepository.findAll(pageable);
    }

    @Override
    public List<BorrowingDto> getOvertimeBorrowing() {
        var borrowingsDb = borrowingRepository.findAll();

        var filteredBorrowings = borrowingsDb.stream()
                .filter(borrowing -> borrowing.getDateOfReturn() == null && borrowing.getDateOfBorrowing().toLocalDate().plusDays(Borrowing.MAX_BORROWING_DAYS).isBefore(ChronoLocalDate.from(LocalDateTime.now()))).collect(Collectors.toList());

        var result = new ArrayList<BorrowingDto>();

        filteredBorrowings.forEach(borrowing -> {
            result.add(BorrowingDto.builder()
                    .dateOfBorrowing(borrowing.getDateOfBorrowing())
                    .bookTitle(borrowing.getBook().getTitle())
                    .dateOfReturn(borrowing.getDateOfReturn())
                    .bookDescription(borrowing.getBook().getDescription())
                    .bookAuthor(borrowing.getBook().getAuthor().getFirstName() + " " + borrowing.getBook().getAuthor().getLastName())
                    .build());
        });

        return result;
    }

    @Override
    public void checkNumberOfActiveBorrowingsByLibraryCardNumber(String libraryCardNumber) {
        var numberOfActiveBorrowings = borrowingRepository.countBorrowingByBorrowerLibraryCardNumberAndDateOfReturnIsNull(libraryCardNumber);

        if (numberOfActiveBorrowings >= 2) {
            throw new TooManyActiveBorrowingsException("Borrower has too many active borrowings");
        }
    }

    @Override
    @Transactional
    public void addBorrowing(String libraryCardNumber, String iban) {

        var bookDb = paperBookService.getPaperBookById(iban);

        var borrowing = Borrowing.builder()
                .dateOfBorrowing(LocalDateTime.now())
                .borrower(borrowerService.getBorrowerByLibraryCardNumber(libraryCardNumber))
                .book(bookDb)
                .build();

        borrowingRepository.save(borrowing);

        bookDb.setQuantity(bookDb.getQuantity() - 1);

        paperBookService.save(bookDb);
    }

    @Override
    public void generateOvertimeReport() {
//        var currentUser = personService.getCurrentUser();

        var overtimeBorrowings = getOvertimeBorrowing();

        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
//            mimeMessageHelper.setTo(currentUser.getEmail());
            mimeMessageHelper.setTo("piter71-1997@o2.pl");
            mimeMessageHelper.setSubject("Overtime borrowings report.");
            mimeMessageHelper.setText("Please find attached overtime borrowings report", false);
            mimeMessageHelper.addAttachment("overtimeBorrowings.csv", new ByteArrayResource(csvFileGenerator.generateFile(getOvertimeBorrowing())));
        });
    }

}
