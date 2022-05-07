package com.kryhowsky.maslibrary.mapper;

import com.kryhowsky.maslibrary.model.dao.Borrower;
import com.kryhowsky.maslibrary.model.dao.Role;
import com.kryhowsky.maslibrary.model.dto.BorrowerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    BorrowerDto toDto(Borrower borrower);

    @Mapping(target = "roles", ignore = true)
    Borrower toDao(BorrowerDto borrowerDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
