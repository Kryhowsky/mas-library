package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import com.kryhowsky.maslibrary.model.dto.AdministratorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    AdministratorDto toDto(Administrator administrator);

    Administrator toDao(AdministratorDto administratorDto);

}
