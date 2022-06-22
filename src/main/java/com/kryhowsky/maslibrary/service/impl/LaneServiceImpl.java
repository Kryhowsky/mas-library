package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.mapper.BookstandMapper;
import com.kryhowsky.maslibrary.model.dao.Lane;
import com.kryhowsky.maslibrary.model.dto.AddBookstandDto;
import com.kryhowsky.maslibrary.repository.BookstandRepository;
import com.kryhowsky.maslibrary.repository.LaneRepository;
import com.kryhowsky.maslibrary.service.LaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LaneServiceImpl implements LaneService {

    private final BookstandRepository bookstandRepository;
    private final BookstandMapper bookstandMapper;
    private final LaneRepository laneRepository;

    @Override
    public Lane save(Lane lane) {
        return laneRepository.save(lane);
    }

    @Override
    @Transactional
    public Lane update(Lane lane, Long id) {

        var laneDb = getLaneById(id);
        laneDb.setSymbol(lane.getSymbol());

        return laneDb;
    }


    @Override
    public Page<Lane> getPage(Pageable pageable) {
        return laneRepository.findAll(pageable);
    }

    @Override
    public Lane getLaneById(Long id) {
        return laneRepository.getById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        var laneDb = getLaneById(id);
        var bookstands = laneDb.getBookstands();

        for (var bookstand : bookstands) {
            bookstandRepository.deleteById(bookstand.getId());
        }

        laneRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addBookstand(long laneId, AddBookstandDto bookstand) {

        var laneDb = getLaneById(laneId);
        var bookstandId = bookstand.getBookstand().getId();

        if (!laneDb.getBookstands().contains(bookstand)) {
            if (bookstandRepository.findById(bookstandId).isEmpty()) {
                bookstandRepository.save(bookstandMapper.toDao(bookstand.getBookstand()));
            }
        }
    }

}
