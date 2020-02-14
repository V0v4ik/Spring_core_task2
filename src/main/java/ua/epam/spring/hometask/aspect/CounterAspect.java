package ua.epam.spring.hometask.aspect;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Aspect
@Component
public class CounterAspect {

    private Map<Object, Long> accessByNameCounter = new HashMap<>();

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..)")
    private void countEventByNameAccess() {
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..)")
    private void countEventPricesQueried() {
    }

    @Pointcut
    private void countEventTicketsBooked() {
    }

    @AfterThrowing

    @AfterReturning(pointcut = "countEventByNameAccess()", returning = "retVal")
    public void increaseCounter(JoinPoint joinPoint, Object retVal) {
        if (accessByNameCounter.containsKey(retVal)) {
            accessByNameCounter.replace(retVal, accessByNameCounter.get(retVal) + 1);
        } else {
            accessByNameCounter.put(retVal, 1L);
        }
    }
}
