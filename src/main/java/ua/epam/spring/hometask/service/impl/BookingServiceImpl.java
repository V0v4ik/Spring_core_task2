package ua.epam.spring.hometask.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Data
public class BookingServiceImpl implements BookingService {

    private double VIP_SEAT_PRICE_MULTIPLIER = 2d;

    private double HIGH_RATED_EVENT_PRICE_MULTIPLIER = 1.2;

    @Autowired
    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        Auditorium auditorium = event.getAuditoriums().get(dateTime);
        byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
        long numOfVipSeats = seats.stream()
                .filter(seat -> auditorium.getVipSeats().contains(seat))
                .count();

        double price = event.getRating().equals(EventRating.HIGH) ?
                event.getBasePrice() * HIGH_RATED_EVENT_PRICE_MULTIPLIER *
                        ((VIP_SEAT_PRICE_MULTIPLIER - 1) * numOfVipSeats + seats.size()) * (1 - discount / 100d)
                : event.getBasePrice() * ((VIP_SEAT_PRICE_MULTIPLIER - 1) * numOfVipSeats + seats.size()) * (1 - discount / 100d);

        return price;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        tickets.forEach(ticket -> {
            if (ticket.getUser() != null) {
                ticket.getUser().getTickets().add(ticket);
            }
            if (!ticket.getEvent().getPurchasedTickets().containsKey(ticket.getDateTime())) {
                ticket.getEvent().getPurchasedTickets().put(ticket.getDateTime(), new HashSet<Ticket>());
            }
            ticket.getEvent().getPurchasedTickets().get(ticket.getDateTime()).add(ticket);
        });
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return event.getPurchasedTickets().get(dateTime);
    }
}
