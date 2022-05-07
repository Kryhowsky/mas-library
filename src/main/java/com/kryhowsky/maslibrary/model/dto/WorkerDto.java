package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.model.dao.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto extends PersonDto {

    @NotBlank
    private String address;

    private Position position;

}
