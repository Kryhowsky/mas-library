package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(indexes = @Index(name = "idx_email", columnList = "email", unique = true))
public class Person extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @NotAudited
    private String password;

    private String firstName;
    private String lastName;
    private Sex sex;

    @ManyToMany
    @JoinTable(name = "person_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
