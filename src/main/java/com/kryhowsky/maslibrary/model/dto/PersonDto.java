package com.kryhowsky.maslibrary.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryhowsky.maslibrary.model.dao.Sex;
import com.kryhowsky.maslibrary.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String maidenName;
    private boolean isBHPCourse;

    @Email
    @NotBlank
    private String email;

    @NotBlank(groups = Create.class)
    @Length(min = 5, groups = Create.class)
    private String password;

    private String confirmPassword;

    private Sex sex;

    private List<String> roles;

}
