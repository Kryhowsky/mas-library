package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(indexes = @Index(name = "idx_email", columnList = "email", unique = true))
public class Person extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    @NotBlank
    protected String email;

    protected String firstName;
    protected String lastName;

    @NotBlank
    protected String password;

    protected String address;
    protected String maidenName;
    protected boolean isBHPCourse;

    protected Sex sex;

    @ManyToMany
    @JoinTable(name = "person_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    protected Set<Role> roles;

}
