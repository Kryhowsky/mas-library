package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;

    private String city;

    @ManyToOne
    @JoinColumn(name = "book_iban")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "publishingHouse_id")
    private PublishingHouse publishingHouse;
}
