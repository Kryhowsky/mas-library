package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
