package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Person;
import com.kryhowsky.maslibrary.model.dao.Role;
import com.kryhowsky.maslibrary.model.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    PersonDto toDto(Person person);

    @Mapping(target = "roles", ignore = true)
    Person toDao(PersonDto personDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
