package ru.belyankina.crudwithboot.services;

import org.springframework.stereotype.Service;
import ru.belyankina.crudwithboot.dao.UserDao;
import ru.belyankina.crudwithboot.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
