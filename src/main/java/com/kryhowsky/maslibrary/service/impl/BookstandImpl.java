package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.mapper.BookstandMapper;
import com.kryhowsky.maslibrary.model.dao.Bookstand;
import com.kryhowsky.maslibrary.model.dto.AddBookstandDto;
import com.kryhowsky.maslibrary.repository.BookstandRepository;
import com.kryhowsky.maslibrary.service.BookstandService;
import com.kryhowsky.maslibrary.service.LaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookstandImpl implements BookstandService {

    private final BookstandRepository bookstandRepository;
    private final LaneService laneService;
    private final BookstandMapper bookstandMapper;

    @Override
    public Bookstand save(AddBookstandDto bookstandDto) {
       laneService.addBookstand(bookstandDto.getLaneId(), bookstandDto);
       return bookstandMapper.toDao(bookstandDto.getBookstand());
    }

    @Override
    @Transactional
    public Bookstand update(Bookstand bookstand, Long id) {

        var bookstandDb = getBookstandById(id);
        bookstandDb.setNumber(bookstand.getNumber());

        return bookstandDb;
    }

    @Override
    public void delete(Long id) {
        bookstandRepository.deleteById(id);
    }

    @Override
    public Page<Bookstand> getPage(Pageable pageable) {
        return bookstandRepository.findAll(pageable);
    }

    @Override
    public Bookstand getBookstandById(Long id) {
        return bookstandRepository.getById(id);
    }
}
