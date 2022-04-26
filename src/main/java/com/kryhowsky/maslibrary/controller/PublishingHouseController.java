package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.PublishingHouseMapper;
import com.kryhowsky.maslibrary.model.dto.PublishingHouseDto;
import com.kryhowsky.maslibrary.service.PublishingHouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/publishing-houses", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;
    private final PublishingHouseMapper publishingHouseMapper;

    @PostMapping
    @Operation(description = "Allows to add new Publishing house.")
    public PublishingHouseDto savePublishingHouse(@RequestBody @Valid PublishingHouseDto publishingHouseDto) {
        return publishingHouseMapper.toDto(publishingHouseService.save(publishingHouseMapper.toDao(publishingHouseDto)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Publishing house by given ID", security = @SecurityRequirement(name = "bearer-key"))
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN'))")
    public PublishingHouseDto getPublishingHouseById(@PathVariable Long id) {
        return publishingHouseMapper.toDto(publishingHouseService.getPublishingHouseById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Returns page of Publishing house with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<PublishingHouseDto> getPublishingHousePage(@RequestParam int page, @RequestParam int size) {
        return publishingHouseService.getPage(PageRequest.of(page, size)).map(publishingHouseMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update Publishing house specified by ID.")
    public PublishingHouseDto updatePublishingHouse(@RequestBody @Valid PublishingHouseDto publishingHouse, @PathVariable Long id) {
        return publishingHouseMapper.toDto(publishingHouseService.update(publishingHouseMapper.toDao(publishingHouse), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete Publishing house specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deletePublishingHouseById(@PathVariable Long id) {
        publishingHouseService.delete(id);
    }

}
