package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.PublishingHouse;
import com.kryhowsky.maslibrary.repository.PublishingHouseRepository;
import com.kryhowsky.maslibrary.service.PublishingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository publishingHouseRepository;

    @Override
    public PublishingHouse save(PublishingHouse publishingHouse) {
        return publishingHouseRepository.save(publishingHouse);
    }

    @Override
    @Transactional
    public PublishingHouse update(PublishingHouse publishingHouse, Long id) {

        var publishingHouseDb = getPublishingHouseById(id);
        publishingHouseDb.setName(publishingHouseDb.getName());

        return publishingHouseDb;
    }

    @Override
    public void delete(Long id) {
        publishingHouseRepository.deleteById(id);
    }

    @Override
    public Page<PublishingHouse> getPage(Pageable pageable) {
        return publishingHouseRepository.findAll(pageable);
    }

    @Override
    public PublishingHouse getPublishingHouseById(Long id) {
        return publishingHouseRepository.getById(id);
    }

}
