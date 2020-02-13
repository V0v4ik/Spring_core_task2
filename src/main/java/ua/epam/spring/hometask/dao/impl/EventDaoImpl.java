package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.db.EventStorage;
import ua.epam.spring.hometask.domain.Event;

import java.util.Collection;

@Repository
public class EventDaoImpl implements EventDao {

    @Override
    public Event getByName(String name) {
        return EventStorage.eventMap.values().stream()
                .filter(event -> event.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event with name: " + name + " doesn't exist"));
    }

    @Override
    public Event save(Event event) {
        EventStorage.eventMap.put(EventStorage.eventCounter++, event);
        return getById(EventStorage.eventCounter);
    }

    @Override
    public void remove(Event event) {
        EventStorage.eventMap.entrySet().removeIf(entry -> entry.getValue().equals(event));
    }

    @Override
    public Event getById(Long id) {
        if(!EventStorage.eventMap.containsKey(id)) {
            throw new IllegalArgumentException("Event with id: " + id + " doesn't exist");
        }
        return EventStorage.eventMap.get(id);
    }

    @Override
    public Collection<Event> getAll() {
        return EventStorage.eventMap.values();
    }
}
