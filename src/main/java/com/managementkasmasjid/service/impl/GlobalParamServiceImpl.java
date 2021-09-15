package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.repository.GlobalParamRepository;
import com.managementkasmasjid.service.GlobalParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GlobalParamServiceImpl implements GlobalParamService {
    @Autowired
    GlobalParamRepository globalParamRepository;

    @Override
    public GlobalParam save(GlobalParam request) {
        log.info("GlobalParam Save");
        GlobalParam result = new GlobalParam();
        try {
            result = globalParamRepository.save(request);
            log.info("GlobalParam Save is successfully");
        } catch (Exception ex) {
            log.info("GlobalParam save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return result;
    }

    @Override
    public GlobalParam update(GlobalParam request) {
        log.info("GlobalParam Update");
        GlobalParam result = globalParamRepository.getById(request.getId());
        if (result != null) {
            globalParamRepository.save(result);
            log.info("GlobalParam Update is successfully");
        } else {
            result = new GlobalParam();
            log.info("GlobalParam is Not Found");
        }
        return result;
    }

    @Override
    public GlobalParam delete(Long id) {
        log.info("GlobalParam Delete");
        GlobalParam result = globalParamRepository.findById(id).orElse(null);
        if (result != null) {
            globalParamRepository.delete(result);
            log.info("GlobalParam Delete is successfully");
        } else {
            result = new GlobalParam();
            log.info("GlobalParam is Not Found");
        }
        return result;
    }

    @Override
    public GlobalParam getById(Long id) {
        log.info("GlobalParam getById");
        GlobalParam result = globalParamRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("GlobalParam getById is successfully");
        } else {
            result = new GlobalParam();
            log.info("GlobalParam is Not Found");
        }
        return result;
    }

    @Override
    public List<GlobalParam> getAll() {
        log.info("GlobalParam getAll");
        List<GlobalParam> result = globalParamRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<GlobalParam> getAllByCondition(String paramCondition) {
        log.info("GlobalParam getAllByCondition");
        List<GlobalParam> result = globalParamRepository.findAllByParamCondition(paramCondition);
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<GlobalParam> getAllByParamDesc(String paramDesc) {
        log.info("GlobalParam getAllByParamDesc");
        List<GlobalParam> result = globalParamRepository.findAllByParamDesc(paramDesc);
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public GlobalParam getByParamConditionAndParamDesc(String paramCondition, String paramDesc) {
        log.info("GlobalParam getByParamConditionAndParamDesc");
        GlobalParam result = globalParamRepository.findByParamConditionAndParamDesc(paramCondition,paramDesc);
        log.info("Data :: " + result);
        return result;
    }

}
