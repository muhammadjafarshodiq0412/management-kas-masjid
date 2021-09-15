package com.managementkasmasjid.service;

import com.managementkasmasjid.entity.GlobalParam;

import java.util.List;

public interface GlobalParamService {
    GlobalParam save(GlobalParam data);
    GlobalParam update(GlobalParam data);
    GlobalParam delete(Long id);
    GlobalParam getById(Long id);
    List<GlobalParam> getAll();
    List<GlobalParam> getAllByCondition(String paramCondition);
    List<GlobalParam> getAllByParamDesc(String paramDesc);
    GlobalParam getByParamConditionAndParamDesc(String paramCondition, String paramDesc);
}
