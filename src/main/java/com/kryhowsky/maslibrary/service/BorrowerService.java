package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Borrower;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BorrowerService {

    Borrower save(Borrower borrower);
    Borrower update(Borrower borrower, Long id);
    void delete(Long id);
    Page<Borrower> getPage(Pageable pageable);
    Borrower getBorrowerById(Long id);

}
