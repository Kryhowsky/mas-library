package com.kryhowsky.maslibrary.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Worker extends Person {

    private Position position;

}
