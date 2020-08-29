package pkg.asembly_line;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pkg.car_components.Car;
import pkg.configs.Config;

public class Assembler {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Car car = applicationContext.getBean(Car.class);
        car.startEngine();
        applicationContext.close();
    }
}
