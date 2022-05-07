package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Role;
import com.kryhowsky.maslibrary.model.dao.Worker;
import com.kryhowsky.maslibrary.model.dto.WorkerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    WorkerDto toDto(Worker worker);

    @Mapping(target = "roles", ignore = true)
    Worker toDao(WorkerDto workerDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
