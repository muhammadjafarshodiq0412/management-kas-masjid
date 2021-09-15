package com.managementkasmasjid.controller.transaction;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("/penerimaan")
    public String indexPenerimaan(Model model){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, GlobalConstant.PENERIMAAN.toUpperCase());
        List<Transaction> data = transactionService.getAllByCategoryTransaction(param);
        model.addAttribute("data", data);
        return "pages/transaction/penerimaan/index";
    }

    @GetMapping("/pengeluaran")
    public String indexPengeluaran(Model model){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, GlobalConstant.PENGELUARAN.toUpperCase());
        List<Transaction> data = transactionService.getAllByCategoryTransaction(param);
        model.addAttribute("data", data);
        return "pages/transaction/pengeluaran/index";
    }
}
