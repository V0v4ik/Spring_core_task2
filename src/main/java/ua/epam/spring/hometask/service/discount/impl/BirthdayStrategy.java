package ua.epam.spring.hometask.service.discount.impl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.discount.DiscountStrategy;

public class BirthdayStrategy implements DiscountStrategy {
    @Override
    public int calculateDiscount() {
        return 0;
    }
}
