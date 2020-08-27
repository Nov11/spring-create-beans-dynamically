package pkg.asembly_line;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pkg.car_components.Car;
import pkg.configs.Config;

public class AssembleFromConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//        applicationContext.addApplicationListener(new AListener());
        applicationContext.register(Config.class);
        applicationContext.refresh();
        Car car = (Car) applicationContext.getBean("car_d");
        car.startEngine();

        Car c10 = (Car) applicationContext.getBean("c10");
        c10.startEngine();
    }
}
