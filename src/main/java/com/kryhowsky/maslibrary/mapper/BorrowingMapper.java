package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Borrowing;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    BorrowingDto toDto(Borrowing borrowing);

    Borrowing toDao(BorrowingDto borrowingDto);

}
