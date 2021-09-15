package com.managementkasmasjid.dto.request;

import lombok.Data;

@Data
public class UserRequestDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long roleId;
}
