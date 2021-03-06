package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.dto.request.JournalRequestDto;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Journal;
import com.managementkasmasjid.entity.Transaction;

import java.util.List;

public interface JournalService {
    Journal save(JournalRequestDto data);
    List<Journal> saveAll(List<Journal> data);
    Journal update(JournalRequestDto data);
    Journal delete(Long id);
    Journal getById(Long id);
    List<Journal> getAll();
    List<Journal> getAllCategoryJournal(GlobalParam param,DownloadFileRequest request);
    void deletByTransaction(Transaction transaction);
}
