package com.managementkasmasjid.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    private String info;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_transaction")
    private GlobalParam categoryTransaction;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "common_user")
    private CommonUser commonUser;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_dana")
    private GlobalParam categoryDana;

    @PrePersist
    protected void onCreated() {
        this.transactionDate = new Date();
    }

    public Transaction() {
    }

    public Transaction(Long amount, String info, User user, GlobalParam categoryTransaction, CommonUser commonUser, GlobalParam categoryDana) {
        this.amount = amount;
        this.info = info;
        this.user = user;
        this.categoryTransaction = categoryTransaction;
        this.commonUser = commonUser;
        this.categoryDana = categoryDana;
    }
}
