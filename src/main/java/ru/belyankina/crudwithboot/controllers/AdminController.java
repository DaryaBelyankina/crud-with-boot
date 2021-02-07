package ru.belyankina.crudwithboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.belyankina.crudwithboot.model.User;
import ru.belyankina.crudwithboot.services.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String start(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("auth",userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("user", new User());
        model.addAttribute("userForDelete", new User());
        model.addAttribute("userNew", new User());
        return "helloPage";
    }


    @PostMapping("/create")
    public String post(@ModelAttribute("userNew") User user){
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String delete(@ModelAttribute("userForDelete") User user){
        userService.deleteUserById(user.getId());
        return "redirect:/admin";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("userForUpdate") User user){
        userService.update(user);
        return "redirect:/admin";
    }
}
