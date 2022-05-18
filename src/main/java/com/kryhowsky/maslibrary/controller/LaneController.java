package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.LaneMapper;
import com.kryhowsky.maslibrary.model.dto.LaneDto;
import com.kryhowsky.maslibrary.service.LaneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/lanes", produces = MediaType.APPLICATION_JSON_VALUE)
public class LaneController {

    private final LaneMapper laneMapper;
    private final LaneService laneService;

    @PostMapping
    @Operation(description = "Allows to add new Lane.")
    public LaneDto saveLane(@RequestBody @Valid LaneDto lane) {
        return laneMapper.toDto(laneService.save(laneMapper.toDao(lane)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Lane by given ID.", security = @SecurityRequirement(name = "bearer-key"))
    public LaneDto getLaneById(@PathVariable Long id) {
        return laneMapper.toDto(laneService.getLaneById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Lane with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<LaneDto> getLanePage(@RequestParam int page, @RequestParam int size) {
        return laneService.getPage(PageRequest.of(page, size)).map(laneMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update Lane specified by ID.")
    public LaneDto updateLane(@RequestBody @Valid LaneDto lane, @PathVariable Long id) {
        return laneMapper.toDto(laneService.update(laneMapper.toDao(lane), id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Paperbook specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteLaneById(@PathVariable Long id) {
        laneService.delete(id);
    }

}
