package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(indexes = @Index(name = "idx_iban", columnList = "iban", unique = true))
public abstract class Book {

    @Id
    private String iban;

    @Column(unique = true)
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
