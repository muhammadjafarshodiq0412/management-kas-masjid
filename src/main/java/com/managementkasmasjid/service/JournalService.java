package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.JournalRequestDto;
import com.managementkasmasjid.entity.Journal;

import java.util.List;

public interface JournalService {
    Journal save(JournalRequestDto data);
    Journal update(JournalRequestDto data);
    Journal delete(Long id);
    Journal getById(Long id);
    List<Journal> getAll();
    List<Journal> getAllCategoryJournal(Long categoryJournal);
}
