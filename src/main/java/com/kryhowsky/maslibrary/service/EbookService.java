package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Ebook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EbookService {

    Ebook save(Ebook ebook);
    Ebook update(Ebook ebook, String iban);
    void delete(String iban);
    Page<Ebook> getPage(Pageable pageable);
    Ebook getEbookById(String iban);

}
