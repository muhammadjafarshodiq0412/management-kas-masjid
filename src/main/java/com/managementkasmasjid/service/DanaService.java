package com.managementkasmasjid.service;

import com.managementkasmasjid.dto.request.DanaRequestDto;
import com.managementkasmasjid.entity.Dana;

import java.util.List;

public interface DanaService {
    Dana save(Dana data);
    Dana update(Dana data);
    Dana delete(Long id);
    Dana getById(Long id);
    List<Dana> getAll();
}
