package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Bookstand;
import com.kryhowsky.maslibrary.model.dto.BookstandDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookstandMapper {

    Bookstand toDao(BookstandDto bookstandDto);
    BookstandDto toDto(Bookstand bookstand);

}
