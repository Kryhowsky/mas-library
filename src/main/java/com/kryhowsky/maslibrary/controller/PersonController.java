package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.PersonMapper;
import com.kryhowsky.maslibrary.model.dto.PersonDto;
import com.kryhowsky.maslibrary.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(value = "/api/persons", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PersonController {

    private final PersonMapper personMapper;
    private final PersonService personService;

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Allows to check information about logged user.", security = @SecurityRequirement(name = "bearer-key"))
    public PersonDto getCurrentUser() {
        return personMapper.toDto(personService.getCurrentUser());
    }

}
