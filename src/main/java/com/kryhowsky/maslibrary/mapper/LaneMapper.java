package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Lane;
import com.kryhowsky.maslibrary.model.dto.LaneDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LaneMapper {

    Lane toDao(LaneDto laneDto);
    LaneDto toDto(Lane lane);
}
