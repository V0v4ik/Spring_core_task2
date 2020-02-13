package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.db.UserStorage;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User getByEmail(String email) {
        return UserStorage.userMap.values().stream()
                .filter(user -> user.getEmail().toLowerCase().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with email: " + email + " doesn't exist"));
    }

    @Override
    public User save(User user) {
        UserStorage.userMap.put(UserStorage.userCounter++, user);
        return getById(UserStorage.userCounter);
    }

    @Override
    public void remove(User user) {
        UserStorage.userMap.entrySet().removeIf(entry -> entry.getValue().equals(user));
    }

    @Override
    public User getById(Long id) {
        if (!UserStorage.userMap.containsKey(id)) {
            throw new IllegalArgumentException("User with id: " + id + " doesn't exist");
        }
        return UserStorage.userMap.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return UserStorage.userMap.values();
    }
}
