package ua.epam.spring.hometask.service.discount.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.service.discount.DiscountStrategy;

@Component
@Qualifier("DiscountStrategy")
public class NumberOfTicketsStrategy implements DiscountStrategy {
    @Override
    public int calculateDiscount() {
        return 0;
    }
}
