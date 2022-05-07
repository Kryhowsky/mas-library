package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Person;
import com.kryhowsky.maslibrary.repository.PersonRepository;
import com.kryhowsky.maslibrary.security.SecurityUtils;
import com.kryhowsky.maslibrary.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person getCurrentUser() {
        return personRepository.findByEmail(SecurityUtils.getCurrentEmailUser()).orElseThrow(EntityNotFoundException::new);
    }

}
