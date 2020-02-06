package ua.epam.spring.hometask.db;

import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

public class EventStorage {
    public static Map<Long, Event> eventMap = new HashMap<>();

    public static Long eventCounter = 0L;
}
