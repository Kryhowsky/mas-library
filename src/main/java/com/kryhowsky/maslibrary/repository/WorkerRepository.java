package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
