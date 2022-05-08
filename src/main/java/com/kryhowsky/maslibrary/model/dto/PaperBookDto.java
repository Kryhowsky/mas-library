package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.model.dao.CoverType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaperBookDto extends BookDto {

    private CoverType coverType;
    private Integer quantity;

}
