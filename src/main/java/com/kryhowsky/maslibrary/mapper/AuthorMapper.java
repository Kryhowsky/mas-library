package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Author;
import com.kryhowsky.maslibrary.model.dao.Role;
import com.kryhowsky.maslibrary.model.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    AuthorDto toDto(Author author);

    @Mapping(target = "roles", ignore = true)
    Author toDao(AuthorDto author);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }


}
