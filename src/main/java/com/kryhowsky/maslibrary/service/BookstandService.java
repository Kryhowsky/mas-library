package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Bookstand;
import com.kryhowsky.maslibrary.model.dto.AddBookstandDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookstandService {

    Bookstand save(AddBookstandDto bookstand);
    Bookstand update(Bookstand bookstand, Long id);
    void delete(Long id);
    Page<Bookstand> getPage(Pageable pageable);
    Bookstand getBookstandById(Long id);

}
