package ru.belyankina.crudwithboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.belyankina.crudwithboot.services.UserService;


@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model){
        model.addAttribute("auth",userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "userPage";
    }

}
