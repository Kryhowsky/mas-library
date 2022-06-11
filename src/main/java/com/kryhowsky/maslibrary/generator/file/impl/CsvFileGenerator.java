package com.kryhowsky.maslibrary.generator.file.impl;

import com.kryhowsky.maslibrary.generator.file.FileGeneratorStrategy;
import com.kryhowsky.maslibrary.generator.model.FileType;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvFileGenerator implements FileGeneratorStrategy {

    @Override
    public FileType getType() {
        return FileType.CSV;
    }

    @Override
    public byte[] generateFile(List<BorrowingDto> borrowings) {
        log.info("Generating CSV file using Generic Strategy...");
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        String[] headers = {"Date of borrowing", "Date of return", "Book title", "Book description", "Book author"};
        csvWriter.writeNext(headers);

        borrowings.forEach(borrowingDto -> {
            String[] content = {borrowingDto.getDateOfBorrowing().toString(), borrowingDto.getDateOfReturn() == null ? "" : borrowingDto.getDateOfReturn().toString(), borrowingDto.getBookTitle(), borrowingDto.getBookDescription(), borrowingDto.getBookAuthor()};
            csvWriter.writeNext(content);
        });

        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }
}
