package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

@Aspect
@Component
public class DiscountAspect {

    @Pointcut
    private void totalDiscountsGivenCounter() {

    }

    @Pointcut
    private void discountsForUserGivenCounter(User user) {

    }
}
