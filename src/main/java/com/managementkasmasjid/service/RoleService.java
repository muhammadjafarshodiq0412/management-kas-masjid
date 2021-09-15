package com.managementkasmasjid.service;

import com.managementkasmasjid.entity.Role;

import java.util.List;

public interface RoleService {
    Role save(Role data);
    Role update(Role data);
    Role delete(Long id);
    Role getById(Long id);
    List<Role> getAll();
}
