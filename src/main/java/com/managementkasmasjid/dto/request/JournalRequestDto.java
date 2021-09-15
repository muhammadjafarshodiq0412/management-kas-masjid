package com.managementkasmasjid.dto.request;

import lombok.Data;

@Data
public class JournalRequestDto {
    private Long id;
    private String journalName;
    private Long debet;
    private Long credit;
    private Long categoryJournal;
    private Long transaction;
}
