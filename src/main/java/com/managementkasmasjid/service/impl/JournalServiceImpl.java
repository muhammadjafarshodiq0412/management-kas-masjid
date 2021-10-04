package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.dto.request.JournalRequestDto;
import com.managementkasmasjid.entity.Journal;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import com.managementkasmasjid.repository.JournalRepository;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.JournalService;
import com.managementkasmasjid.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JournalServiceImpl implements JournalService {
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    TransactionService transactionService;
    @Autowired
    GlobalParamService globalParamService;

    @Override
    public Journal save(JournalRequestDto request) {
        log.info("Journal save");
        Journal journal = new Journal();
        GlobalParam param = globalParamService.getById(request.getCategoryJournal());
        Transaction transaction = transactionService.getById(request.getTransaction());
        try {
            BeanUtils.copyProperties(request, journal, "categoryJournal","transaction");
            journal.setCategoryJournal(param);
            journal.setTransaction(transaction);
            journalRepository.save(journal);
            log.info("Journal save is successfully");
        } catch (Exception ex) {
            log.info("Journal saveDebet is failed");
            log.info("Error : " + ex.getMessage());
        }
        return journal;
    }

    @Override
    public List<Journal> saveAll(List<Journal> data) {
        log.info("Journal save");
        List<Journal> journals = new ArrayList<>();
        try {
            journals = journalRepository.saveAll(data);
            log.info("Journal saveAll is successfully");
        } catch (Exception ex) {
            log.info("Error : " + ex.getMessage());
        }
        return journals;
    }

    @Override
    public Journal update(JournalRequestDto request) {
        log.info("Journal saveDebet");
        Journal journal = journalRepository.findById(request.getId()).orElse(null);
        GlobalParam param = globalParamService.getById(request.getCategoryJournal());
        Transaction transaction = transactionService.getById(request.getTransaction());
        try {
            BeanUtils.copyProperties(request, journal, "categoryJournal","transaction");
            journal.setCategoryJournal(param);
            journal.setTransaction(transaction);
            journalRepository.save(journal);
            log.info("Journal saveDebet is successfully");
        } catch (Exception ex) {
            log.info("Journal saveDebet is failed");
            log.info("Error : " + ex.getMessage());
        }
        return journal;
    }

    @Override
    public Journal delete(Long id) {
        log.info("Journal Delete");
        Journal result = journalRepository.findById(id).orElse(null);
        if (result != null) {
            journalRepository.delete(result);
            log.info("Journal Delete is successfully");
        } else {
            result = new Journal();
            log.info("Journal is Not Found");
        }
        return result;
    }

    @Override
    public Journal getById(Long id) {
        log.info("Journal getById");
        Journal result = journalRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("Journal getById is successfully");
        } else {
            result = new Journal();
            log.info("Journal is Not Found");
        }
        return result;
    }

    @Override
    public List<Journal> getAll() {
        log.info("Journal getAll");
        List<Journal> result = journalRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<Journal> getAllCategoryJournal(GlobalParam param, DownloadFileRequest request) {
        log.info("Journal getAllCategoryJournal Date : "+request.getFromDate() +" - "+request.getUntilDate());
        List<Journal> result = journalRepository.findAllByCategoryJournal(param,request);
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public void deletByTransaction(Transaction transaction) {
        log.info("Journal deletByTransaction : "+transaction.getId());
        journalRepository.deleteByTransaction(transaction);
    }

}
