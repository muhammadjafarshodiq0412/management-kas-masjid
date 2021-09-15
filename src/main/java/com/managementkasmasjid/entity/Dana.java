package com.managementkasmasjid.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Dana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cash;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_cash")
    private GlobalParam categoryCash;

    public Dana() {
    }

    public Dana(Long cash, GlobalParam categoryCash) {
        this.cash = cash;
        this.categoryCash = categoryCash;
    }

    @Override
    public String toString() {
        return "Dana{" +
                "id=" + id +
                ", cash=" + cash +
                ", categoryCash=" + categoryCash +
                '}';
    }
}
