package ua.epam.spring.hometask.aspect.dao;

import ua.epam.spring.hometask.aspect.domain.EventAccessCounter;

public interface EventAspectDao {

    EventAccessCounter getByName(String eventName);

    void save(EventAccessCounter eventAccessCounter);

    void update(EventAccessCounter eventAccessCounter);

}
