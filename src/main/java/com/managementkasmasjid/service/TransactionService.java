package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.dto.request.TransactionDto;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(TransactionDto data);
    Transaction update(Transaction data);
    Transaction delete(Long id);
    Transaction getById(Long id);
    List<Transaction> getAll();
    List<Transaction> getAllByCategoryDana(Long categoryDana);
    List<Transaction> getAllByCategoryTransaction(GlobalParam categoryTransaction);
    List<Transaction> getAllByDate(GlobalParam globalParam,DownloadFileRequest downloadFileRequest);
    Long getSaldo();
}
