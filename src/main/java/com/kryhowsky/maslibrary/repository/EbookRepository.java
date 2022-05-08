package com.kryhowsky.maslibrary.repository;

import com.kryhowsky.maslibrary.model.dao.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<Ebook, String> {
}
