package ua.epam.spring.hometask.aspect.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.epam.spring.hometask.domain.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventAccessCounter {

    private String eventName;

    private long accessByNameCounter;

    private long queryPriceCounter;

    private long bookedTicketsCounter;
}
