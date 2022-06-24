package com.kryhowsky.maslibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String firstName;
    private String lastName;
    private List<String> pseudonym;

}
