package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Lane;
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
    public void delete(Long id) {
        laneRepository.deleteById(id);
    }

    @Override
    public Page<Lane> getPage(Pageable pageable) {
        return laneRepository.findAll(pageable);
    }

    @Override
    public Lane getLaneById(Long id) {
        return laneRepository.getById(id);
    }

}
