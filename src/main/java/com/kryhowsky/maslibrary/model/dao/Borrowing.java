package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Audited
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

    @ManyToOne
    @JoinColumn(name = "borrower_libraryCardNumber")
    private Borrower borrower;

}
