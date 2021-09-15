package com.managementkasmasjid.repository;

import com.managementkasmasjid.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal,Long> {
    List<Journal> findAllByCategoryJournal(Long categoryJournal);
}
