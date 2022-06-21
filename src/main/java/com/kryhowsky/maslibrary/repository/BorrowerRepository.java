package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Borrower;
import com.kryhowsky.maslibrary.model.projections.BorrowerWithBorrowings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {

    BorrowerWithBorrowings findBorrowerById(Long id);
    Optional<Borrower> findByLibraryCardNumber(String libraryCardNumber);

}
