package com.managementkasmasjid.controller.master;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.entity.CommonUser;
import com.managementkasmasjid.entity.Dana;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.service.CommonUserService;
import com.managementkasmasjid.service.DanaService;
import com.managementkasmasjid.service.GlobalParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/master/muzakki")
public class MuzakkiController {
    @Autowired
    private CommonUserService commonUserService;
    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("data", commonUserService.getAllMuzakki());
        return "pages/master/muzakki/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<GlobalParam> params = globalParamService.getAllByParamDesc(GlobalConstant.MUZAKKI.toUpperCase());
        model.addAttribute("data",new CommonUser());
        model.addAttribute("params",params);
        return "pages/master/muzakki/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("data") CommonUser request, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            model.addAttribute("data",request);
        }else{
            this.commonUserService.save(request);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("data", new CommonUser());
        }

        return "redirect:/master/muzakki";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        this.commonUserService.delete(id);
        return "redirect:/master/muzakki";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        CommonUser data = this.commonUserService.getById(id);
        List<GlobalParam> params = globalParamService.getAllByParamDesc(GlobalConstant.MUZAKKI.toUpperCase());
        model.addAttribute("data",data);
        model.addAttribute("params",params);
        return "pages/master/muzakki/edit";
    }

    @PostMapping("/update")
    public String  update(@ModelAttribute("data") CommonUser request, RedirectAttributes redirectAttributes){
        this.commonUserService.update(request);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/master/muzakki";
    }
}
