package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.validator.PasswordValid;
import com.kryhowsky.maslibrary.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@PasswordValid(message = "Password and ConfirmPassword should be the same", groups = Create.class)
public class BorrowerDto extends PersonDto {

    private String libraryCardNumber;

}
