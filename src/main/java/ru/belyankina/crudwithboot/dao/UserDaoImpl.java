package ru.belyankina.crudwithboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.belyankina.crudwithboot.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("my_pass_encode")
    PasswordEncoder encoder;

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User userForDel = entityManager.find(User.class, id);
        if (userForDel != null) {
            entityManager.remove(userForDel);
        }
    }

    @Transactional
    @Override
    public void update(User user) {
        if (user.getPassword() != ""){
            user.setPassword(encoder.encode(user.getPassword()));
        } else {
            user.setPassword(entityManager.find(User.class, user.getId()).getPassword());
        }
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public User getByEmail(String email) {
        return (User) entityManager.createQuery("from User where email = :email").setParameter("email", email).getSingleResult();
    }
}
