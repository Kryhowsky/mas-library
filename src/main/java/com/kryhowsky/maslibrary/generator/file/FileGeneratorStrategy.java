package com.kryhowsky.maslibrary.generator.file;

import com.kryhowsky.maslibrary.generator.GenericStrategy;
import com.kryhowsky.maslibrary.generator.model.FileType;
import com.kryhowsky.maslibrary.model.dto.BorrowingDto;

import java.util.List;

public interface FileGeneratorStrategy extends GenericStrategy<FileType> {

    byte[] generateFile(List<BorrowingDto> borrowings);

}
