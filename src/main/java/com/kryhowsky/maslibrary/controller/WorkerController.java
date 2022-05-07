package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.mapper.WorkerMapper;
import com.kryhowsky.maslibrary.model.dto.WorkerDto;
import com.kryhowsky.maslibrary.service.WorkerService;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/workers", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkerController {

    private final WorkerMapper workerMapper;
    private final WorkerService workerService;

    @PostMapping
    @Validated(Create.class)
    @Operation(description = "Allows to add new Worker.")
    public WorkerDto saveWorker(@RequestBody @Valid WorkerDto worker) {
        return workerMapper.toDto(workerService.save(workerMapper.toDao(worker)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Worker by given ID.", security = @SecurityRequirement(name = "bearer-key"))
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerMapper.toDto(workerService.getWorkerById(id));
    }

    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Returns page of Workers with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<WorkerDto> getWorkerPage(@RequestParam int page, @RequestParam int size) {
        return workerService.getPage(PageRequest.of(page, size)).map(workerMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update administrator specified by ID.")
    public WorkerDto updateWorker(@RequestBody @Valid WorkerDto worker, @PathVariable Long id) {
        return workerMapper.toDto(workerService.update(workerMapper.toDao(worker), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete Worker specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteWorkerById(@PathVariable Long id) {
        workerService.delete(id);
    }

}
