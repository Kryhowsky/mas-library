package com.kryhowsky.maslibrary.service;

import com.kryhowsky.maslibrary.model.dao.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkerService {

    Worker save(Worker worker);
    Worker update(Worker worker, Long id);
    void delete(Long id);
    Page<Worker> getPage(Pageable pageable);
    Worker getWorkerById(Long id);

}
