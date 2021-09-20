package com.managementkasmasjid.controller.transaction;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.dto.request.TransactionDto;
import com.managementkasmasjid.entity.CommonUser;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Transaction;
import com.managementkasmasjid.service.CommonUserService;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.TransactionService;
import com.managementkasmasjid.utils.NominalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private GlobalParamService globalParamService;
    @Autowired
    private CommonUserService commonUserService;

    //PENERIMAAN
    @GetMapping("/penerimaan")
    public String indexPenerimaan(Model model){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, GlobalConstant.PENERIMAAN.toUpperCase());
        List<Transaction> data = transactionService.getAllByCategoryTransaction(param);
        Long saldo = transactionService.getSaldo();
        model.addAttribute("terbilang",NominalUtils.nominalCharacter(saldo) + " Rupiah");
        model.addAttribute("saldo",NominalUtils.nominalToIdrCurrency(saldo));
        model.addAttribute("data", data);
        return "pages/transaction/penerimaan/index";
    }

    @GetMapping("/penerimaan/add")
    public String add(Model model){
        List<CommonUser> params = commonUserService.getAllMuzakki();
        model.addAttribute("data",new TransactionDto());
        model.addAttribute("params",params);
        return "pages/transaction/penerimaan/add";
    }

    @PostMapping("/penerimaan/save")
    public String save(@Valid @ModelAttribute("data") TransactionDto request, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION,GlobalConstant.PENERIMAAN);
        if (result.hasErrors()){
            model.addAttribute("data",request);
        }else{
            request.setCategoryTransaction(param);
            this.transactionService.save(request);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("data", new CommonUser());
        }

        return "redirect:/transaction/penerimaan";
    }

    @GetMapping("/penerimaan/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        this.transactionService.delete(id);
        return "redirect:/transaction/penerimaan";
    }

    @GetMapping("/penerimaan/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Transaction data = this.transactionService.getById(id);
        List<CommonUser> params = commonUserService.getAllMuzakki();
        model.addAttribute("data",data);
        model.addAttribute("params",params);
        return "pages/transaction/penerimaan/edit";
    }

    @PostMapping("/penerimaan/update")
    public String  update(@ModelAttribute("data") Transaction request, RedirectAttributes redirectAttributes){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION,GlobalConstant.PENERIMAAN);
        request.setCategoryTransaction(param);
        this.transactionService.update(request);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/transaction/penerimaan";
    }

    //PENGELUARAN
    @GetMapping("/pengeluaran")
    public String indexPengeluaran(Model model){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION, GlobalConstant.PENGELUARAN.toUpperCase());
        List<Transaction> data = transactionService.getAllByCategoryTransaction(param);
        Long saldo = transactionService.getSaldo();
        model.addAttribute("terbilang",NominalUtils.nominalCharacter(saldo)+ " Rupiah");
        model.addAttribute("saldo",NominalUtils.nominalToIdrCurrency(saldo));
        model.addAttribute("data", data);
        return "pages/transaction/pengeluaran/index";
    }

    @GetMapping("/pengeluaran/add")
    public String addPengeluaran(Model model){
        List<CommonUser> params = commonUserService.getAllMustahik();
        model.addAttribute("data",new TransactionDto());
        model.addAttribute("params",params);
        return "pages/transaction/pengeluaran/add";
    }

    @PostMapping("/pengeluaran/save")
    public String savePengeluaran(@Valid @ModelAttribute("data") TransactionDto request, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION,GlobalConstant.PENGELUARAN);
        if (result.hasErrors()){
            model.addAttribute("data",request);
        }else{
            request.setCategoryTransaction(param);
            this.transactionService.save(request);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("data", new CommonUser());
        }

        return "redirect:/transaction/pengeluaran";
    }

    @GetMapping("/pengeluaran/delete/{id}")
    public String deletePengeluaran(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        this.transactionService.delete(id);
        return "redirect:/transaction/pengeluaran";
    }

    @GetMapping("/pengeluaran/edit/{id}")
    public String ediPpengeluaran(@PathVariable("id") Long id, Model model){
        Transaction data = this.transactionService.getById(id);
        List<CommonUser> params = commonUserService.getAllMustahik();
        model.addAttribute("data",data);
        model.addAttribute("params",params);
        return "pages/transaction/pengeluaran/edit";
    }

    @PostMapping("/pengeluaran/update")
    public String updatePengeluaran(@ModelAttribute("data") Transaction request, RedirectAttributes redirectAttributes){
        GlobalParam param = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_TRANSACTION,GlobalConstant.PENGELUARAN);
        request.setCategoryTransaction(param);
        this.transactionService.update(request);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/transaction/pengeluaran";
    }
}
