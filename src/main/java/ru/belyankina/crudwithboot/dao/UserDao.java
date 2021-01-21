package ru.belyankina.crudwithboot.dao;


import ru.belyankina.crudwithboot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    void update(User user);
    User getByEmail(String email);
}
