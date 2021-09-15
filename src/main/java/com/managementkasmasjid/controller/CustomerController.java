package com.managementkasmasjid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
//    @Autowired
//    private CustomerService customerService;

//    @GetMapping(value = "")
//    private String index(Model model){
//        List<CustomerTab> list = this.customerService.get();
//        model.addAttribute("data", list);
//        return "pages/customer/index.html";
//    }
}
