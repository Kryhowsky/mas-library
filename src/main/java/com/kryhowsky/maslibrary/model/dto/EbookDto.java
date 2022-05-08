package com.kryhowsky.maslibrary.model.dto;

import com.kryhowsky.maslibrary.model.dao.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EbookDto extends BookDto {

    private Format format;

}
