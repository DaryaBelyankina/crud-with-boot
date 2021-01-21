package ru.belyankina.crudwithboot.services;


import ru.belyankina.crudwithboot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    void update(User user);
    User getByEmail(String email);
}
