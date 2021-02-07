package ru.belyankina.crudwithboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.belyankina.crudwithboot.model.User;
import ru.belyankina.crudwithboot.services.UserService;

import java.util.List;

@RestController
@RequestMapping("admin/rest")
public class NewController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllusers(){
        return userService.getAllUsers();
    }

    @PostMapping("users/new")
    public boolean createUser(@RequestBody User user){
        userService.addUser(user);
        return true;
    }

    @DeleteMapping("users/delete")
    public boolean deleteUser(@RequestBody User user){
        userService.deleteUserById(user.getId());
        return true;
    }

    @PatchMapping("/users/update")
    public boolean updateUser(@RequestBody User user){
        userService.update(user);
        return true;
    }
}
