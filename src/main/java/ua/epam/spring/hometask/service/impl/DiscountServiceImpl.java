package ua.epam.spring.hometask.service.impl;

import lombok.Data;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.discount.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiscountServiceImpl implements DiscountService {

    //TODO rewrite discount strategies, think how to do it correct
    private List<DiscountStrategy> discountStrategies;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return (byte) discountStrategies.stream()
                .mapToInt(DiscountStrategy::calculateDiscount)
                .max()
                .orElse(0);
    }

}
