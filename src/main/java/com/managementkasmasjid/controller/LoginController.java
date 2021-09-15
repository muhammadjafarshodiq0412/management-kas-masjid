package com.managementkasmasjid.controller;

import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.service.RoleService;
import com.managementkasmasjid.service.UserService;
import com.managementkasmasjid.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping(value = "")
    public String login(){
        return "pages/login";
    }


}
