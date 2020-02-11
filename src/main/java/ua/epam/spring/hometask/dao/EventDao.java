package ua.epam.spring.hometask.dao;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.db.EventStorage;
import ua.epam.spring.hometask.domain.Event;

import java.util.Collection;

@Repository
public class EventDao {

    public Event getByName(String name) {
        return EventStorage.eventMap.values().stream()
                .filter(event -> event.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Event with name: " + name + " doesn't exist"));
    }

    public Event save(Event event) {
        EventStorage.eventMap.put(EventStorage.eventCounter++, event);
        return getById(EventStorage.eventCounter);
    }

    public void remove(Event event) {
        EventStorage.eventMap.entrySet().removeIf(entry -> entry.getValue().equals(event));
    }

    public Event getById(Long id) {
        if(!EventStorage.eventMap.containsKey(id)) {
            throw new IllegalArgumentException("Event with id: " + id + " doesn't exist");
        }
        return EventStorage.eventMap.get(id);
    }

    public Collection<Event> getAllEvents() {
        return EventStorage.eventMap.values();
    }
}
