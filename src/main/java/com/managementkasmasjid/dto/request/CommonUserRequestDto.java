package com.managementkasmasjid.dto.request;

import lombok.Data;

@Data
public class CommonUserRequestDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private Long categoryCommonUser;
}
