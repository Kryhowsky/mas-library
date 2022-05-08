package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Ebook;
import com.kryhowsky.maslibrary.repository.EbookRepository;
import com.kryhowsky.maslibrary.service.EbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {

    private final EbookRepository ebookRepository;

    @Override
    public Ebook save(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    @Override
    @Transactional
    public Ebook update(Ebook ebook, String iban) {

        var ebookDb = getEbookById(iban);
        ebookDb.setDescription(ebook.getDescription());
        ebookDb.setTitle(ebook.getTitle());
        ebookDb.setFormat(ebook.getFormat());

        return ebookDb;
    }

    @Override
    public void delete(String iban) {
        ebookRepository.deleteById(iban);
    }

    @Override
    public Page<Ebook> getPage(Pageable pageable) {
        return ebookRepository.findAll(pageable);
    }

    @Override
    public Ebook getEbookById(String iban) {
        return ebookRepository.getById(iban);
    }

}
