package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.PaperBook;
import com.kryhowsky.maslibrary.model.dto.PaperBookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaperBookMapper {

    PaperBookDto toDto(PaperBook paperBook);

    PaperBook toDao(PaperBookDto paperBookDto);

}
