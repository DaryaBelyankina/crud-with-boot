package ru.belyankina.crudwithboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.belyankina.crudwithboot.model.Role;
import ru.belyankina.crudwithboot.model.User;
import ru.belyankina.crudwithboot.services.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String start(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("userForDelete", new User());
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("userForUpdate", new User());
        return "helloPage";
    }

    @PostMapping("/create")
    public String post(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String delete(@ModelAttribute("userForDelete") User user){
        userService.deleteUserById(user.getId());
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String getUpdatePage(@PathVariable long id, Model model){
        model.addAttribute("userForUpdate", userService.getUserById(id));
        return "updateUser";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("userForUpdate") User user){
        userService.update(user);
        return "redirect:/admin";
    }
}
