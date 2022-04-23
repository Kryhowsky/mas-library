package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import com.kryhowsky.maslibrary.repository.RoleRepository;
import com.kryhowsky.maslibrary.repository.UserRepository;
import com.kryhowsky.maslibrary.security.SecurityUtils;
import com.kryhowsky.maslibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
//    private final MailService mailService;

    @Override
    public Administrator save(Administrator administrator) {
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> administrator.setRoles(Collections.singleton(role)));
//        user.setActivationToken(UUID.randomUUID().toString());
        var result = userRepository.save(administrator);
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("link", backendLink + "/api/users/activate?token=" + user.getActivationToken());
//        mailService.sendEmail(variables, "greetingsMail", user.getEmail());
        return result;
    }

    @Override
    @Transactional
    public Administrator update(Administrator administrator, Long id) {

        var userDb = getUserById(id);
        userDb.setEmail(administrator.getEmail());
        userDb.setFirstName(administrator.getFirstName());
        userDb.setLastName(administrator.getLastName());

        return userDb;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<Administrator> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Administrator getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Administrator getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentEmailUser()).orElseThrow(EntityNotFoundException::new);
    }

}