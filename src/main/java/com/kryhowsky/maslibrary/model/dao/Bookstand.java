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
public class Bookstand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lane_id")
    private Lane lane;

    @OneToMany(mappedBy = "bookstand")
    private Set<Book> books;

    private int number;
}
