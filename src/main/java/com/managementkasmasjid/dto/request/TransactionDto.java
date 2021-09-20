package com.managementkasmasjid.dto.request;

import com.managementkasmasjid.entity.GlobalParam;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDto {
    private Long id;
    private Long amount;
    private String info;
    private GlobalParam categoryTransaction;
    private Long commonUser;

}
