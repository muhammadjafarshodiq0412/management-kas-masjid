package com.managementkasmasjid.repository;

import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Journal;
import com.managementkasmasjid.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal,Long> {
    @Query(value = "SELECT j.* FROM journal j join transaction t ON j.transaction = t.id " +
            "where j.category_journal =  :#{#param.id} " +
            "and " +
            "DATE_FORMAT(t.transaction_date, '%Y-%m-%d') between DATE_FORMAT( :#{#request.fromDate}, '%Y-%m-%d') AND DATE_FORMAT(:#{#request.untilDate}, '%Y-%m-%d') " +
            "order by j.transaction ASC;", nativeQuery = true)
    List<Journal> findAllByCategoryJournal(@Param("param") GlobalParam param, @Param("request") DownloadFileRequest request);

    void deleteByTransaction(Transaction transaction);
}
