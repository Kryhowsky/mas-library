package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private Set<Borrowing> borrowings;

    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Borrower borrower = (Borrower) o;

        if (libraryCardNumber != null ? !libraryCardNumber.equals(borrower.libraryCardNumber) : borrower.libraryCardNumber != null)
            return false;
        return address != null ? address.equals(borrower.address) : borrower.address == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (libraryCardNumber != null ? libraryCardNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
