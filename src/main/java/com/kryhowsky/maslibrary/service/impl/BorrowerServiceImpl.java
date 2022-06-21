package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.mapper.BorrowerMapper;
import com.kryhowsky.maslibrary.model.dao.Borrower;
import com.kryhowsky.maslibrary.model.dto.BorrowerWithBorrowingsDto;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import com.kryhowsky.maslibrary.repository.BorrowerRepository;
import com.kryhowsky.maslibrary.repository.RoleRepository;
import com.kryhowsky.maslibrary.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final BorrowerMapper borrowerMapper;

    @Override
    public Borrower save(Borrower borrower) {
        borrower.setPassword(passwordEncoder.encode(borrower.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> borrower.setRoles(Collections.singleton(role)));
        var result = borrowerRepository.save(borrower);
        return result;
    }

    @Override
    @Transactional
    public Borrower update(Borrower borrower, Long id) {

        var borrowerDb = getBorrowerById(id);
        borrowerDb.setEmail(borrower.getEmail());
        borrowerDb.setFirstName(borrower.getFirstName());
        borrowerDb.setLastName(borrower.getLastName());
        borrowerDb.setLibraryCardNumber(borrower.getLibraryCardNumber());
        borrowerDb.setAddress(borrower.getAddress());

        return borrowerDb;
    }

    @Override
    public void delete(Long id) {
        borrowerRepository.deleteById(id);
    }

    @Override
    public Page<Borrower> getPage(Pageable pageable) {
        return borrowerRepository.findAll(pageable);
    }

    @Override
    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.getById(id);
    }

    @Override
    public Borrower getBorrowerByLibraryCardNumber(String libraryCardNumber) {
        return borrowerRepository.findByLibraryCardNumber(libraryCardNumber).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BorrowerWithBorrowingsDto getBorrowerWithBorrowingsById(Long id) {

        var borrowerDb = borrowerRepository.getById(id);
        var borrowingsDb = borrowerDb.getBorrowings();

        var borrowings = borrowingsDb.stream()
                .map(borrowing -> BorrowingDto.builder()
                    .bookTitle(borrowing.getBook().getTitle())
//                    .bookAuthor(borrowing.getBook().getAuthor().getPseudonym())
                    .bookAuthor("")
                    .bookDescription(borrowing.getBook().getDescription())
                    .dateOfBorrowing(borrowing.getDateOfBorrowing())
                    .dateOfReturn(borrowing.getDateOfReturn())
                    .build())
                .collect(Collectors.toList());

        var result = BorrowerWithBorrowingsDto.builder()
                .borrowings((List<BorrowingDto>) borrowings)
                .address(borrowerDb.getAddress())
                .email(borrowerDb.getEmail())
                .firstName(borrowerDb.getFirstName())
                .lastName(borrowerDb.getLastName())
                .sex(borrowerDb.getSex())
                .libraryCardNumber(borrowerDb.getLibraryCardNumber())
                .borrower(borrowerMapper.toDto(borrowerDb))
                .build();

        return result;
    }

}
