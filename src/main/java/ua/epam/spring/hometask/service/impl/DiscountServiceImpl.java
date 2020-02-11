package ua.epam.spring.hometask.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.discount.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    //TODO rewrite discount strategies, think how to do it correct
    //and how to autowire collection
    @Autowired
    @Qualifier("DiscountStrategy")
    private List<DiscountStrategy> discountStrategies;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return (byte) discountStrategies.stream()
                .mapToInt(DiscountStrategy::calculateDiscount)
                .max()
                .orElse(0);
    }

}
