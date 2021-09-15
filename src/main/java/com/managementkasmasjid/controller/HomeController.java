package com.managementkasmasjid.controller;

import com.managementkasmasjid.service.RoleService;
import com.managementkasmasjid.service.UserService;
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

    @GetMapping(value = "dashboard")
    public String dashboard(Model model) {
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
