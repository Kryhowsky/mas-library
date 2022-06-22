package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Book {

    @Id
    private String isbn;

    private String title;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Edition> editions;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<Borrowing> borrowings;

    @ManyToOne
    @JoinColumn(name = "bookstand_id")
    private Bookstand bookstand;

    private String description;

}
