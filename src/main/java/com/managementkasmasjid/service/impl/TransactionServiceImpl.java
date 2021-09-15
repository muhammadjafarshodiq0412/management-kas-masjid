package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.CommonUserRequestDto;
import com.managementkasmasjid.dto.request.DanaRequestDto;
import com.managementkasmasjid.dto.request.TransactionDto;
import com.managementkasmasjid.entity.*;
import com.managementkasmasjid.repository.*;
import com.managementkasmasjid.service.*;
import com.managementkasmasjid.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CommonUserService commonUserService;
    @Autowired
    GlobalParamService globalParamService;
    @Autowired
    UserService userService;
    @Autowired
    DanaService danaService;

    @Override
    public Transaction save(TransactionDto request) {
        log.info("Transaction Save");
        Transaction result = new Transaction();
        User user = userService.getById(request.getUser());
        GlobalParam param = globalParamService.getById(request.getCategoryTransaction());
        CommonUser commonUser = commonUserService.getById(request.getCommonUser());
        Dana dana = danaService.getById(request.getCategoryDana());
        try {
            BeanUtils.copyProperties(request, result, "user", "categoryTransaction", "commonUser", "categoryDana", "transactionDate");
            result.setUser(user);
            result.setCommonUser(commonUser);
//            Dana danaRequestDto = new Dana();
//            danaRequestDto.setId(dana.getId());

//            danaRequestDto.setCategoryCash((dana.getCategoryCash().getId()));
            if (param.getParamCondition().equalsIgnoreCase(GlobalConstant.PENERIMAAN)) {
                dana.setCash(dana.getCash() + request.getAmount());
            } else {
                dana.setCash(dana.getCash() - request.getAmount());
            }
            danaService.update(dana); //update cash
            result.setCategoryTransaction(param);
            result.setCategoryDana(dana.getCategoryCash());
            transactionRepository.save(result);
            log.info("Transaction Save is successfully");
        } catch (Exception ex) {
            log.info("Transaction save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Transaction update(TransactionDto request) {
        log.info("Transaction Save");
        Transaction result = transactionRepository.findById(request.getId()).orElse(null);
        User user = userService.getById(request.getUser());
        GlobalParam param = globalParamService.getById(request.getCategoryTransaction());
        CommonUser commonUser = commonUserService.getById(request.getCommonUser());
        Dana dana = danaService.getById(request.getCategoryDana());
        try {
            BeanUtils.copyProperties(request, result, "user", "categoryTransaction", "commonUser", "categoryDana", "transactionDate");
            result.setUser(user);
            result.setCommonUser(commonUser);
//            DanaRequestDto danaRequestDto = new DanaRequestDto();
//            danaRequestDto.setId(dana.getId());

//            danaRequestDto.setCategoryCash((dana.getCategoryCash().getId()));
            if (param.getParamCondition().equalsIgnoreCase(GlobalConstant.PENERIMAAN)) {
                dana.setCash(dana.getCash() + request.getAmount());
            } else {
                dana.setCash(dana.getCash() - request.getAmount());
            }
            danaService.update(dana); //update cash
            result.setCategoryTransaction(param);
            result.setCategoryDana(dana.getCategoryCash());
            transactionRepository.save(result);
            log.info("Transaction Save is successfully");
        } catch (Exception ex) {
            log.info("Transaction save is failed");
            log.info("Error : " + ex.getMessage());
        }
        return result;
    }

    @Override
    public Transaction delete(Long id) {
        log.info("Transaction Delete");
        Transaction result = transactionRepository.findById(id).orElse(null);
        if (result != null) {
            transactionRepository.delete(result);
            log.info("Transaction Delete is successfully");
        } else {
            result = new Transaction();
            log.info("Transaction is Not Found");
        }
        return result;
    }

    @Override
    public Transaction getById(Long id) {
        log.info("Transaction getById");
        Transaction result = transactionRepository.findById(id).orElse(null);
        if (result != null) {
            log.info("Transaction getById is successfully");
        } else {
            result = new Transaction();
            log.info("Transaction is Not Found");
        }
        return result;
    }

    @Override
    public List<Transaction> getAll() {
        log.info("Transaction getAll");
        List<Transaction> result = transactionRepository.findAll();
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<Transaction> getAllByCategoryDana(Long categoryDana) {
        log.info("Transaction getAllByCategoryDana");
        List<Transaction> result = transactionRepository.findAllByCategoryDana(categoryDana);
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public List<Transaction> getAllByCategoryTransaction(GlobalParam categoryTransaction) {
        log.info("Transaction getAllByCategoryTransaction");
        List<Transaction> result = new ArrayList<>();
        try {
            result = transactionRepository.findAllByCategoryTransaction(categoryTransaction);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        log.info("Data :: " + result.size());
        return result;
    }

}
