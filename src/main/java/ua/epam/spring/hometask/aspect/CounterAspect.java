package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {

    @Pointcut("execution(* *.)")
    private void eventByNameAccessCounter() {

    }

    @Pointcut
    private void eventPricesQueriedCounter() {

    }

    @Pointcut
    private void eventTicketsBookedCounter() {

    }
}
