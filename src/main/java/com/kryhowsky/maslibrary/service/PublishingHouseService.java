package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.PublishingHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublishingHouseService {

    PublishingHouse save(PublishingHouse publishingHouse);
    PublishingHouse update(PublishingHouse publishingHouse, Long id);
    void delete(Long id);
    Page<PublishingHouse> getPage(Pageable pageable);
    PublishingHouse getPublishingHouseById(Long id);

}
