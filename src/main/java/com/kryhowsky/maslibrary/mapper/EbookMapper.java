package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Ebook;
import com.kryhowsky.maslibrary.model.dto.EbookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EbookMapper {

    EbookDto toDto(Ebook ebook);

    Ebook toDao(EbookDto ebookDto);

}
