package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.CommonUserRequestDto;
import com.managementkasmasjid.entity.CommonUser;

import java.util.List;

public interface CommonUserService {
    CommonUser save(CommonUser data);
    CommonUser update(CommonUser data);
    CommonUser delete(Long id);
    CommonUser getById(Long id);
    List<CommonUser> getAll();
    List<CommonUser> getAllMustahik();
    List<CommonUser> getAllMuzakki();
}
