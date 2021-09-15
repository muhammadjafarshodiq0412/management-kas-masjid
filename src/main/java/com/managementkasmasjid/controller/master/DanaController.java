package com.managementkasmasjid.controller.master;

import com.managementkasmasjid.constant.GlobalConstant;
import com.managementkasmasjid.entity.Dana;
import com.managementkasmasjid.entity.GlobalParam;
import com.managementkasmasjid.entity.Role;
import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.service.DanaService;
import com.managementkasmasjid.service.GlobalParamService;
import com.managementkasmasjid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/master/dana")
public class DanaController {
    @Autowired
    private DanaService danaService;
    @Autowired
    private GlobalParamService globalParamService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("data", danaService.getAll());
        return "pages/master/dana/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<GlobalParam> params = globalParamService.getAllByCondition(GlobalConstant.CATEGORY_DANA);
        model.addAttribute("data",new Dana());
        model.addAttribute("params",params);
        return "pages/master/dana/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("data") Dana dana, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            model.addAttribute("data",dana);
        }else{
            this.danaService.save(dana);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("data", new User());
        }

        return "redirect:/master/dana";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        this.danaService.delete(id);
        return "redirect:/master/dana";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        Dana data = this.danaService.getById(id);
        List<GlobalParam> params = globalParamService.getAllByCondition(GlobalConstant.CATEGORY_DANA);
        model.addAttribute("data",data);
        model.addAttribute("params",params);
        return "pages/master/dana/edit";
    }

    @PostMapping("/update")
    public String  update(@ModelAttribute("data") Dana request, RedirectAttributes redirectAttributes){
        this.danaService.update(request);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/master/dana";
    }
}
