package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.UserRequestDto;
import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.entity.User;

import java.util.List;

public interface UserService {
    User save(User data);
    User update(User data);
    User delete(Long id);
    User getById(Long id);
    List<User> getAll();
    User getByUsername(String username);
}
