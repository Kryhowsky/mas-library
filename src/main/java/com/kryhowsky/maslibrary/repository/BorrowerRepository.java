package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
