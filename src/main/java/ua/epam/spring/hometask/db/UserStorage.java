package ua.epam.spring.hometask.db;

import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    public static Map<Long, User> userMap = new HashMap<>();

    public static Long userCounter = 0L;
}
