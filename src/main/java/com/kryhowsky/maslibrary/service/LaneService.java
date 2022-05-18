package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Lane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaneService {

    Lane save(Lane lane);
    Lane update(Lane lane, Long id);
    void delete(Long id);
    Page<Lane> getPage(Pageable pageable);
    Lane getLaneById(Long id);

}
