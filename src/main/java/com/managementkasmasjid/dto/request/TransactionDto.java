package com.managementkasmasjid.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private Long amount;
    private Date transactionDate;
    private String info;
    private Long user;
    private Long categoryTransaction;
    private Long commonUser;
    private Long categoryDana;

}
