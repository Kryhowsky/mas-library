package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@Audited
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Borrower extends Person {

    @Column(unique = true)
    private String libraryCardNumber;

    @OneToMany(mappedBy = "borrower")
    private Set<Borrowing> borrowings;

    private String address;

}
