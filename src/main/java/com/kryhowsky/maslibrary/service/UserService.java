package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Administrator save(Administrator administrator);
    Administrator update(Administrator administrator, Long id);
    void delete(Long id);
    Page<Administrator> getPage(Pageable pageable);
    Administrator getUserById(Long id);
    Administrator getCurrentUser();

}
