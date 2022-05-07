package com.kryhowsky.maslibrary.service.impl;

import com.kryhowsky.maslibrary.model.dao.Worker;
import com.kryhowsky.maslibrary.repository.RoleRepository;
import com.kryhowsky.maslibrary.repository.WorkerRepository;
import com.kryhowsky.maslibrary.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final PasswordEncoder passwordEncoder;
    private final WorkerRepository workerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Worker save(Worker worker) {
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        roleRepository.findByName("ROLE_WORKER").ifPresent(role -> worker.setRoles(Collections.singleton(role)));

        var result = workerRepository.save(worker);

        return result;
    }

    @Override
    @Transactional
    public Worker update(Worker worker, Long id) {

        var workerDb = getWorkerById(id);
        workerDb.setEmail(worker.getEmail());
        workerDb.setFirstName(worker.getFirstName());
        workerDb.setLastName(worker.getLastName());
        workerDb.setAddress(worker.getAddress());
        workerDb.setPosition(worker.getPosition());

        return workerDb;
    }

    @Override
    public void delete(Long id) {
        workerRepository.deleteById(id);
    }

    @Override
    public Page<Worker> getPage(Pageable pageable) {
        return workerRepository.findAll(pageable);
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.getById(id);
    }

}
