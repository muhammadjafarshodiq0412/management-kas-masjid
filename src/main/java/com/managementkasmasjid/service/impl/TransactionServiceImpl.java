package com.managementkasmasjid.service.impl;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.DownloadFileRequest;
import com.managementkasmasjid.dto.request.TransactionDto;
import com.managementkasmasjid.entity.*;
import com.managementkasmasjid.repository.*;
import com.managementkasmasjid.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    IAuthenticationFacade iAuthenticationFacade;

    @Override
    public Transaction save(TransactionDto request) {
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        log.info("Transaction Save By User : "+ authentication.getName());
        Transaction result = new Transaction();
        User user = userService.getByUsername(authentication.getName());
        GlobalParam danaParam = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, request.getCategoryTransaction().getParamDesc());
        CommonUser commonUser = commonUserService.getById(request.getCommonUser());
        Dana dana = danaService.getByCategoryCash(danaParam);
        try {
            BeanUtils.copyProperties(request, result, "user", "categoryTransaction", "commonUser", "categoryDana", "transactionDate");
            result.setUser(user);
            result.setCommonUser(commonUser);
            if (request.getCategoryTransaction().getParamDesc().equalsIgnoreCase(GlobalConstant.PENERIMAAN)) {
                dana.setCash(dana.getCash() + request.getAmount());
            } else {
                dana.setCash(dana.getCash() + request.getAmount());
            }
            danaService.update(dana); //update cash
            result.setCategoryTransaction(request.getCategoryTransaction());
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
    public Transaction update(Transaction request) {
        Authentication authentication = iAuthenticationFacade.getAuthentication();
        log.info("Transaction Save By User : "+ authentication.getName());
        Transaction result = transactionRepository.findById(request.getId()).orElse(null);
        User user = userService.getByUsername(authentication.getName());
        GlobalParam danaParam = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, request.getCategoryTransaction().getParamDesc());
        CommonUser commonUser = commonUserService.getById(request.getCommonUser().getId());
        Dana dana = danaService.getByCategoryCash(danaParam);
        try {
            BeanUtils.copyProperties(request, result, "user", "commonUser","categoryDana", "transactionDate");
            result.setUser(user);
            result.setCommonUser(commonUser);
            if (request.getCategoryTransaction().getParamDesc().equalsIgnoreCase(GlobalConstant.PENERIMAAN)) {
                dana.setCash(dana.getCash() + request.getAmount());
            } else {
                dana.setCash(dana.getCash() + request.getAmount());
            }
            danaService.update(dana); //update cash
            result.setCategoryTransaction(request.getCategoryTransaction());
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

    @Override
    public List<Transaction> getAllByDate(GlobalParam globalParam, DownloadFileRequest downloadFileRequest) {
        log.info("Transaction getAllByDate : "+downloadFileRequest.getFromDate() +" - "+downloadFileRequest.getUntilDate());
        List<Transaction> result = new ArrayList<>();
        try {
            result = transactionRepository.findTransactionAlltByCategoryTransactionAndDate(globalParam,downloadFileRequest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        log.info("Data :: " + result.size());
        return result;
    }

    @Override
    public Long getSaldo() {
        log.info("getSaldo Started..");
        GlobalParam penerimaan = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, GlobalConstant.PENERIMAAN.toUpperCase());
        GlobalParam pengeluaran = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, GlobalConstant.PENGELUARAN.toUpperCase());
        Dana danaPenerimaan = danaService.getByCategoryCash(penerimaan);
        Dana danaPengeluaran = danaService.getByCategoryCash(pengeluaran);
        Long saldo = danaPenerimaan.getCash() - danaPengeluaran.getCash();
        log.info("Hitung Saldo : "+danaPenerimaan.getCash()+" - "+danaPengeluaran.getCash()+" = "+saldo);
        log.info("getSaldo Accomplish..");
        return saldo;
    }

}
