package com.kryhowsky.maslibrary.controller;

import com.kryhowsky.maslibrary.generator.GenericFactory;
import com.kryhowsky.maslibrary.generator.file.FileGeneratorStrategy;
import com.kryhowsky.maslibrary.generator.model.FileType;
import com.kryhowsky.maslibrary.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {

    private final GenericFactory<FileType, FileGeneratorStrategy> genericFactory;
    private final BorrowingService borrowingService;

    @GetMapping("/generic")
    @Operation(description = "Sends csv report")
    public void testGenericFlyweight(@RequestParam FileType fileType) { // use ResponseEntity only when custom headers needed
//        byte[] file = genericFactory.getStrategyByType(fileType).generateFile();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length));
//        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report." + fileType.name().toLowerCase());
//        return ResponseEntity.ok().headers(httpHeaders).body(file);

        borrowingService.generateOvertimeReport();
//        return ResponseEntity.ok();
    }

}
