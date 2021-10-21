package com.managementkasmasjid.repository;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByCategoryDana(Long categoryDana);
    List<Transaction> findAllByCategoryTransaction(GlobalParam categoryTransaction);
    @Query(value = "SELECT t.* FROM transaction t WHERE t.category_transaction = :#{#param.id} AND DATE_FORMAT(t.transaction_date, '%Y-%m-%d') between DATE_FORMAT(:#{#request.fromDate}, '%Y-%m-%d') AND DATE_FORMAT(:#{#request.untilDate}, '%Y-%m-%d')", nativeQuery = true)
    List<Transaction> findTransactionAlltByCategoryTransactionAndDate(@Param("param") GlobalParam param, @Param("request") DownloadFileRequest request);
    @Query(value = "SELECT SUM(t.amount) FROM transaction t WHERE t.category_transaction = :#{#param.id}", nativeQuery = true)
    Long sumTransactionByCategory(@Param("param") GlobalParam param);
}
