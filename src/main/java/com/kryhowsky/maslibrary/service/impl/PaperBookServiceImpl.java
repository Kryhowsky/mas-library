package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.PaperBook;
import com.kryhowsky.maslibrary.repository.PaperBookRepository;
import com.kryhowsky.maslibrary.service.PaperBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaperBookServiceImpl implements PaperBookService {

    private final PaperBookRepository paperBookRepository;

    @Override
    public PaperBook save(PaperBook paperBook) {
        return paperBookRepository.save(paperBook);
    }

    @Override
    @Transactional
    public PaperBook update(PaperBook paperBook, String iban) {

        var paperBookDb = getPaperBookById(iban);
        paperBookDb.setDescription(paperBook.getDescription());
        paperBookDb.setTitle(paperBook.getTitle());
        paperBookDb.setQuantity(paperBook.getQuantity());
        paperBookDb.setCoverType(paperBook.getCoverType());

        return paperBookDb;
    }

    @Override
    public void delete(String iban) {
        paperBookRepository.deleteById(iban);
    }

    @Override
    public Page<PaperBook> getPage(Pageable pageable) {
        return paperBookRepository.findAll(pageable);
    }

    @Override
    public PaperBook getPaperBookById(String iban) {
        return paperBookRepository.getById(iban);
    }

}
