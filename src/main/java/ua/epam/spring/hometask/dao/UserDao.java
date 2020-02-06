package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.db.UserStorage;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

public class UserDao {

    public User getUserByEmail(String email) {
        return UserStorage.userMap.values().stream()
                .filter(user -> user.getEmail().toLowerCase().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with email: " + email + " doesn't exist"));
    }

    public User save(User user) {
        UserStorage.userMap.put(UserStorage.userCounter++, user);
        return getById(UserStorage.userCounter);
    }

    public void remove(User user) {
        UserStorage.userMap.entrySet().removeIf(entry -> entry.getValue().equals(user));
    }

    public User getById(Long id) {
        if (!UserStorage.userMap.containsKey(id)) {
            throw new IllegalArgumentException("User with id: " + id + " doesn't exist");
        }
        return UserStorage.userMap.get(id);
    }

    public Collection<User> getAllUsers() {
        return UserStorage.userMap.values();
    }
}
