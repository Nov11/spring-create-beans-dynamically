package pkg.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import pkg.car_components.Car;
import pkg.car_components.Engine;
import pkg.car_components.Wheel;
import pkg.car_components.Window;

@Component
public class DynamicallyCreateCarConfig implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinitionBuilder engine1 = BeanDefinitionBuilder.genericBeanDefinition(Engine.class);
        engine1.addPropertyValue("name", "vvv");
        BeanDefinitionBuilder wheel1 = BeanDefinitionBuilder.genericBeanDefinition(Wheel.class);
        wheel1.addPropertyValue("diameter", 100);
        BeanDefinitionBuilder window1 = BeanDefinitionBuilder.genericBeanDefinition(Window.class);
        window1.addConstructorArgValue(200);
        beanDefinitionRegistry.registerBeanDefinition("e1", engine1.getBeanDefinition());
        beanDefinitionRegistry.registerBeanDefinition("window1", window1.getBeanDefinition());
        beanDefinitionRegistry.registerBeanDefinition("wheel1", wheel1.getBeanDefinition());

        BeanDefinitionBuilder car1 = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        car1.addPropertyReference("engine", "e1");
        car1.addPropertyReference("window", "window1");
        car1.addPropertyReference("wheel", "wheel1");
        beanDefinitionRegistry.registerBeanDefinition("car_d", car1.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
