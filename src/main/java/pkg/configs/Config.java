package pkg.configs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pkg.car_components.Car;
import pkg.car_components.Engine;
import pkg.car_components.Wheel;
import pkg.car_components.Window;

import static org.slf4j.LoggerFactory.getLogger;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@ComponentScan
public class Config {
    private static final Logger logger = getLogger(Config.class);
    @Bean
    Engine engine(){
        return new Engine("v0");
    }

    @Bean
    Car car() {
        Car car = new Car();
        car.setEngine(engine());
        car.setWheel(new Wheel(0));
        car.setWindow(new Window(0));
        return car;
    }

    @Aspect
    static class AopLogger {
        @Pointcut("execution (* pkg.car_components.Engine.start(..))")
        public void start() {
        }

        @Around("start()")
        public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
            System.out.println("before calling start");
            Object ret = null;
            try {
                ret = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                logger.error("error", throwable);
            }
            System.out.println("after calling start");
            return ret;
        }
    }

    @Bean
    AopLogger aopLogger() {
        return new AopLogger();
    }
}

