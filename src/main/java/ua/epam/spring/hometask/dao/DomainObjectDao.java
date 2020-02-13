package ua.epam.spring.hometask.dao;

import java.util.Collection;

public interface DomainObjectDao<T> {

    T save(T t);

    void remove (T t);

    T getById(Long id);

    Collection<T> getAll();
}
