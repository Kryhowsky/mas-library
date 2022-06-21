package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Author;
import com.kryhowsky.maslibrary.model.dto.AuthorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toDao(AuthorDto author);


}
