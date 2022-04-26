package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.PublishingHouse;
import com.kryhowsky.maslibrary.model.dto.PublishingHouseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublishingHouseMapper {

    PublishingHouse toDao(PublishingHouseDto publishingHouseDto);
    PublishingHouseDto toDto(PublishingHouse publishingHouse);

}
