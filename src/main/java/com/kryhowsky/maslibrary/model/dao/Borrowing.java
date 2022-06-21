package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfBorrowing;

    private LocalDateTime dateOfReturn;

    @ManyToOne
    @JoinColumn(name = "book_iban")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_libraryCardNumber")
    private Borrower borrower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrowing borrowing = (Borrowing) o;

        if (id != null ? !id.equals(borrowing.id) : borrowing.id != null) return false;
        if (dateOfBorrowing != null ? !dateOfBorrowing.equals(borrowing.dateOfBorrowing) : borrowing.dateOfBorrowing != null)
            return false;
        if (dateOfReturn != null ? !dateOfReturn.equals(borrowing.dateOfReturn) : borrowing.dateOfReturn != null)
            return false;
        if (book != null ? !book.equals(borrowing.book) : borrowing.book != null) return false;
        return borrower != null ? borrower.equals(borrowing.borrower) : borrowing.borrower == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateOfBorrowing != null ? dateOfBorrowing.hashCode() : 0);
        result = 31 * result + (dateOfReturn != null ? dateOfReturn.hashCode() : 0);
        return result;
    }
}
