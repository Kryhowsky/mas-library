package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import com.kryhowsky.maslibrary.model.dao.Role;
import com.kryhowsky.maslibrary.model.dto.AdministratorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    AdministratorDto toDto(Administrator administrator);

    @Mapping(target = "roles", ignore = true)
    Administrator toDao(AdministratorDto administratorDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
