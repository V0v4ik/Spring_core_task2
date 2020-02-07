package ua.epam.spring.hometask.service.discount.impl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.discount.DiscountStrategy;

public class NumberOfTicketsStrategy implements DiscountStrategy {
    @Override
    public int calculateDiscount() {
        return 0;
    }
}
