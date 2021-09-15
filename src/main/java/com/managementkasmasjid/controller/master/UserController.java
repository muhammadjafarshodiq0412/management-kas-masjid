package com.managementkasmasjid.controller.master;

import com.managementkasmasjid.entity.Role;
import com.managementkasmasjid.entity.User;
import com.managementkasmasjid.service.RoleService;
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
@RequestMapping("/master/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("data", userService.getAll());
        return "pages/master/user/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        List<Role> roles = roleService.getAll();
        model.addAttribute("user",new User());
        model.addAttribute("roles",roles);
        return "pages/master/user/add";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result,Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            model.addAttribute("user",user);
        }else{
            this.userService.save(user);
            redirectAttributes.addFlashAttribute("message", "Congratulation");
            redirectAttributes.addFlashAttribute("flash", "Save");
            model.addAttribute("user", new User());
        }

        return "redirect:/master/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        redirectAttributes.addFlashAttribute("flash", "Delete");
        User delete = this.userService.delete(id);
        return "redirect:/master/user";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long user, Model model){
        User data = this.userService.getById(user);
        List<Role> roles = roleService.getAll();
        model.addAttribute("user",data);
        model.addAttribute("roles",roles);
        return "pages/master/user/edit";
    }

    @PostMapping("/update")
    public String  update(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes){
        User update = this.userService.update(user);
        redirectAttributes.addFlashAttribute("flash", "Update");
        redirectAttributes.addFlashAttribute("message", "Congratulation");
        return "redirect:/master/user";
    }
}
