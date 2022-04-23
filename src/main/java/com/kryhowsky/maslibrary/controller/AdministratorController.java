package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.AdministratorMapper;
import com.kryhowsky.maslibrary.model.dto.AdministratorDto;
import com.kryhowsky.maslibrary.service.UserService;
import com.kryhowsky.maslibrary.validator.group.Create;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/administrators", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdministratorController { // warstwa do komunikacji z klientem

    private final AdministratorMapper administratorMapper;
    private final UserService userService;

    @PostMapping
    @Validated(Create.class)
    @Operation(description = "Allows to add new Administrator.")
    public AdministratorDto saveAdministrator(@RequestBody @Valid AdministratorDto administrator) {
        return administratorMapper.toDto(userService.save(administratorMapper.toDao(administrator)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns administrator by given ID", security = @SecurityRequirement(name = "bearer-key"))
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public AdministratorDto getAdministratorById(@PathVariable Long id) {
        return administratorMapper.toDto(userService.getUserById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Returns page of Administrators with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<AdministratorDto> getAdministratorPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(administratorMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update administrator specified by ID.")
    public AdministratorDto updateAdministrator(@RequestBody @Valid AdministratorDto administrator, @PathVariable Long id) {
        return administratorMapper.toDto(userService.update(administratorMapper.toDao(administrator), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete administrator specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteAdministratorById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Allows to check information about logged administrator.", security = @SecurityRequirement(name = "bearer-key"))
    public AdministratorDto getCurrentUser() {
        return administratorMapper.toDto(userService.getCurrentUser());
    }

}
