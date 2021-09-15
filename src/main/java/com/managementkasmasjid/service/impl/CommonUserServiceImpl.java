package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.CommonUserRequestDto;
import com.managementkasmasjid.entity.CommonUser;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.repository.CommonUserRepository;
import com.managementkasmasjid.repository.GlobalParamRepository;
import com.managementkasmasjid.service.CommonUserService;
import com.managementkasmasjid.service.CommonUserService;
import com.managementkasmasjid.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommonUserServiceImpl implements CommonUserService {
    @Autowired
    CommonUserRepository commonUserRepository;
    @Autowired
    CommonUtil util;
    @Autowired
    GlobalParamRepository globalParamRepository;

    @Override
    public CommonUser save(CommonUser request) {
        log.info("CommonUser Save");
        CommonUser result = new CommonUser();
        GlobalParam paramDto = globalParamRepository.getById(request.getCategoryCommonUser().getId());
        try {
            BeanUtils.copyProperties(request, result, "categoryCommonUser");
            result.setCategoryCommonUser(paramDto);
            commonUserRepository.save(result);
            log.info("CommonUser Save is successfully");
        } catch (Exception ex) {
            log.info("CommonUser save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return result;
    }

    @Override
    public CommonUser update(CommonUser request) {
        log.info("CommonUser Update");
        CommonUser result = commonUserRepository.findById(request.getId()).orElse(null);
        GlobalParam paramDto = globalParamRepository.getById(request.getCategoryCommonUser().getId());
        if (result != null) {
            BeanUtils.copyProperties(request, result, "categoryCash");
            result.setCategoryCommonUser(paramDto);
            commonUserRepository.save(result);
            log.info("CommonUser Update is successfully");
        } else {
            result = new CommonUser();
            log.info("CommonUser is Not Found");
        }
        return result;
    }

    @Override
    public CommonUser delete(Long id) {
        log.info("CommonUser Delete");
        CommonUser result = commonUserRepository.findById(id).orElse(null);
        if (result != null) {
            commonUserRepository.delete(result);
            log.info("CommonUser Delete is successfully");
        } else {
            result = new CommonUser();
            log.info("CommonUser is Not Found");
        }
        return result;
    }

    @Override
    public CommonUser getById(Long id) {
        log.info("CommonUser getById");
        CommonUser result = commonUserRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("CommonUser getById is successfully");
        } else {
            result = new CommonUser();
            log.info("CommonUser is Not Found");
        }
        return result;
    }

    @Override
    public List<CommonUser> getAll() {
        log.info("CommonUser getAll");
        List<CommonUser> result = commonUserRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<CommonUser> getAllMustahik() {
        log.info("CommonUser getAllMustahik");
        GlobalParam param = globalParamRepository.findByParamConditionAndParamDesc("category_common_user",GlobalConstant.MUSTAHIK);
        List<CommonUser> result = commonUserRepository.findAllByCategoryCommonUser(param.getId());
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<CommonUser> getAllMuzakki() {
        log.info("CommonUser getAllMuzakki");
        GlobalParam param = globalParamRepository.findByParamConditionAndParamDesc("category_common_user",GlobalConstant.MUZAKKI);
        List<CommonUser> result = commonUserRepository.findAllByCategoryCommonUser(param.getId());
        log.info("Data :: " + result.size());
        return result;
    }
}
