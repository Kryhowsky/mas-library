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
public class Lane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "lane")
    private Set<Bookstand> bookstands;

    private char symbol;

}
