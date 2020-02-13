package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

public interface UserDao extends DomainObjectDao<User> {

    User getByEmail(String email);
}
