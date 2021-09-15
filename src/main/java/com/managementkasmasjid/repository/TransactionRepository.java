package com.managementkasmasjid.repository;

import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByCategoryDana(Long categoryDana);
    List<Transaction> findAllByCategoryTransaction(GlobalParam categoryTransaction);
}
