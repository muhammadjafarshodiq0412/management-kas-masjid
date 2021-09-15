package com.managementkasmasjid.controller.master;

import com.managementkasmasjid.entity.CommonUser;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.service.GlobalParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/master/global-param")
public class GlobalParamController {
    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("data", globalParamService.getAll());
        return "pages/master/global_param/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("data",new GlobalParam());
        return "pages/master/global_param/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("data") GlobalParam request, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            model.addAttribute("data",request);
        }else{
            this.globalParamService.save(request);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("data", new CommonUser());
        }

        return "redirect:/master/global-param";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        this.globalParamService.delete(id);
        return "redirect:/master/global-param";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        GlobalParam data = this.globalParamService.getById(id);
        model.addAttribute("data",data);
        return "pages/master/global_param/edit";
    }

    @PostMapping("/update")
    public String  update(@ModelAttribute("data") GlobalParam request, RedirectAttributes redirectAttributes){
        this.globalParamService.update(request);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/master/global-param";
    }
}
