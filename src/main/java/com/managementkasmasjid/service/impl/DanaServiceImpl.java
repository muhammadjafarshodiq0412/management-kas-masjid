package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.dto.request.DanaRequestDto;
import com.managementkasmasjid.entity.Dana;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.repository.DanaRepository;
import com.managementkasmasjid.repository.GlobalParamRepository;
import com.managementkasmasjid.service.DanaService;
import com.managementkasmasjid.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DanaServiceImpl implements DanaService {
    @Autowired
    DanaRepository danaRepository;
    @Autowired
    CommonUtil util;
    @Autowired
    GlobalParamRepository globalParamRepository;

    @Override
    public Dana save(Dana request) {
        log.info("Dana Save");
        Dana dana = new Dana();
        GlobalParam paramDto = globalParamRepository.getById(request.getCategoryCash().getId());
        try {
            BeanUtils.copyProperties(request, dana, "categoryCash");
            dana.setCategoryCash(paramDto);
            danaRepository.save(dana);
            log.info("Dana Save is successfully");
        } catch (Exception ex) {
            log.info("Dana save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return dana;
    }

    @Override
    public Dana update(Dana request) {
        log.info("Dana Update");
        Dana result = danaRepository.findById(request.getId()).orElse(null);
        GlobalParam paramDto = globalParamRepository.getById(request.getCategoryCash().getId());
        if (result != null) {
            BeanUtils.copyProperties(request, result, "categoryCash");
            result.setCategoryCash(paramDto);
            danaRepository.save(result);
            log.info("Dana Update is successfully");
        } else {
            result = new Dana();
            log.info("Dana is Not Found");
        }
        return result;
    }

    @Override
    public Dana delete(Long id) {
        log.info("Dana Delete");
        Dana result = danaRepository.findById(id).orElse(null);
        if (result != null) {
            danaRepository.delete(result);
            log.info("Dana Delete is successfully");
        } else {
            result = new Dana();
            log.info("Dana is Not Found");
        }
        return result;
    }

    @Override
    public Dana getById(Long id) {
        log.info("Dana getById");
        Dana result = danaRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("Dana getById is successfully");
        } else {
            result = new Dana();
            log.info("Dana is Not Found");
        }
        return result;
    }

    @Override
    public List<Dana> getAll() {
        log.info("Dana getAll");
        List<Dana> result = danaRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }
}
