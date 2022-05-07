package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Administrator;
import com.kryhowsky.maslibrary.repository.RoleRepository;
import com.kryhowsky.maslibrary.repository.AdministratorRepository;
import com.kryhowsky.maslibrary.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
//    private final MailService mailService;

    @Override
    public Administrator save(Administrator administrator) {
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        roleRepository.findByName("ROLE_ADMIN").ifPresent(role -> administrator.setRoles(Collections.singleton(role)));
//        user.setActivationToken(UUID.randomUUID().toString());
        var result = administratorRepository.save(administrator);
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("link", backendLink + "/api/users/activate?token=" + user.getActivationToken());
//        mailService.sendEmail(variables, "greetingsMail", user.getEmail());
        return result;
    }

    @Override
    @Transactional
    public Administrator update(Administrator administrator, Long id) {

        var administratorDb = getAdministratorById(id);
        administratorDb.setEmail(administrator.getEmail());
        administratorDb.setFirstName(administrator.getFirstName());
        administratorDb.setLastName(administrator.getLastName());
        administratorDb.setAddress(administrator.getAddress());

        return administratorDb;
    }

    @Override
    public void delete(Long id) {
        administratorRepository.deleteById(id);
    }

    @Override
    public Page<Administrator> getPage(Pageable pageable) {
        return administratorRepository.findAll(pageable);
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        return administratorRepository.getById(id);
    }

}