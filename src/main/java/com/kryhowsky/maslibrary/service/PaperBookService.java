package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.PaperBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaperBookService {

    PaperBook save(PaperBook paperBook);
    PaperBook update(PaperBook paperBook, String iban);
    void delete(String iban);
    Page<PaperBook> getPage(Pageable pageable);
    PaperBook getPaperBookById(String iban);

}
