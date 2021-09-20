package com.managementkasmasjid.controller;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.service.DanaService;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.RoleService;
import com.managementkasmasjid.service.UserService;
import com.managementkasmasjid.utils.NominalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    DanaService danaService;
    @Autowired
    GlobalParamService globalParamService;

    @GetMapping(value = "dashboard")
    public String dashboard(Model model) {
        GlobalParam penerimaan = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, GlobalConstant.PENERIMAAN.toUpperCase());
        GlobalParam pengeluaran = globalParamService.getByParamConditionAndParamDesc(GlobalConstant.CATEGORY_DANA, GlobalConstant.PENGELUARAN.toUpperCase());
        model.addAttribute("cashIn", NominalUtils.nominalToIdrCurrency(danaService.getByCategoryCash(penerimaan).getCash()));
        model.addAttribute("cashOut",NominalUtils.nominalToIdrCurrency(danaService.getByCategoryCash(pengeluaran).getCash()));
        model.addAttribute("userCount", userService.getAll().size());
        model.addAttribute("roleCount", roleService.getAll().size());
        return "pages/home/dashboard";
    }

    @GetMapping(value = "home")
    public String index() {
        return "pages/home/index";
    }

    @GetMapping(value = "home/download")
    public String download() {
        return "pages/home/download";
    }

}
