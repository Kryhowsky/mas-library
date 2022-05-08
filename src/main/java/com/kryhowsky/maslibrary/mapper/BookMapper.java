package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Book;
import com.kryhowsky.maslibrary.model.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

}
