package com.managementkasmasjid.controller.master;

import com.managementkasmasjid.entity.Role;
import com.managementkasmasjid.service.RoleService;
import com.managementkasmasjid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/master/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("data", roleService.getAll());
        return "pages/master/role/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("role",new Role());
        return "pages/master/role/add";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("role") Role role, BindingResult result, HttpSession session){
        ModelAndView view =new ModelAndView("pages/master/role/add");
        if (result.hasErrors()){
            view.addObject("role",role);
        }else{
            session.setAttribute("message", "Congratulation");
            session.setAttribute("flash", "Save");
            this.roleService.save(role);
            view.addObject("role", new Role());
        }

        return view;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session){
        session.setAttribute("message", "Congratulation");
        session.setAttribute("flash", "Delete");
        Role delete = this.roleService.delete(id);
        return "redirect:/master/role";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long role, Model model){
        Role data = this.roleService.getById(role);
        model.addAttribute("role",data);
        return "pages/master/role/edit";
    }

    @PostMapping("/update")
    public String  update(@ModelAttribute("role") Role role, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Update");
            this.roleService.update(role);
        return "redirect:/master/role";
    }

}
