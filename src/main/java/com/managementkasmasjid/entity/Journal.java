package com.managementkasmasjid.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private Long debet;
    private Long credit;
    @OneToOne()
    @JoinColumn(name = "category_journal")
    private GlobalParam categoryJournal;
    @OneToOne()
    @JoinColumn(name = "transaction")
    private Transaction transaction;

    public Journal() {
    }

    public Journal(String account, Long debet, Long credit, GlobalParam categoryJournal, Transaction transaction) {
        this.account = account;
        this.debet = debet;
        this.credit = credit;
        this.categoryJournal = categoryJournal;
        this.transaction = transaction;
    }
}
