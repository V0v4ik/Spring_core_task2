package ua.epam.spring.hometask.aspect;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.aspect.dao.EventAspectDao;
import ua.epam.spring.hometask.aspect.domain.EventAccessCounter;
import ua.epam.spring.hometask.domain.Event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Aspect
@Component
public class CounterAspect {

    @Autowired
    private EventAspectDao eventAspectDao;

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..))")
    private void countEventByNameAccess() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    private void countEventPricesQueried() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    private void countEventTicketsBooked() {
    }

    @AfterReturning(pointcut = "countEventByNameAccess()", returning = "retVal")
    public void increaseByNameAccessCounter(JoinPoint joinPoint, Object retVal) {
        Event event = (Event) retVal;
        EventAccessCounter accessCounter = eventAspectDao.getByName(event.getName());
        if (accessCounter != null) {
            accessCounter.setAccessByNameCounter(accessCounter.getAccessByNameCounter() + 1);
            eventAspectDao.update(accessCounter);
        } else {
            eventAspectDao.save(new EventAccessCounter(event.getName(), 1L, 0, 0));
        }
    }

    @AfterReturning(pointcut = "countEventPricesQueried()")
    public void increaseQueriedPriceCounter(JoinPoint joinPoint) {
        Event event = (Event) Arrays.stream(joinPoint.getArgs()).findFirst().get();
        EventAccessCounter accessCounter = eventAspectDao.getByName(event.getName());
        if (accessCounter != null) {
            accessCounter.setQueryPriceCounter(accessCounter.getQueryPriceCounter() + 1);
            eventAspectDao.update(accessCounter);
        } else {
            eventAspectDao.save(new EventAccessCounter(event.getName(), 0, 1L, 0));
        }
    }
}
