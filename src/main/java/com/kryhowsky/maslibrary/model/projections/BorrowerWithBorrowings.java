package com.kryhowsky.maslibrary.model.projections;

import com.kryhowsky.maslibrary.model.dao.Sex;

import java.util.Set;

public interface BorrowerWithBorrowings {

    String getFirstName();
    String getLastName();
    String getEmail();
    Sex getSex();
    String getAddress();
    String getLibraryCardNumber();
    Set<Borrowing> getBorrowings();

}
