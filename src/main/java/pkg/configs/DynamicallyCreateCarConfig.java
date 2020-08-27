package pkg.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.stereotype.Component;
import pkg.car_components.Car;
import pkg.car_components.Engine;
import pkg.car_components.Wheel;
import pkg.car_components.Window;

import java.util.ArrayList;
import java.util.List;

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
        beanDefinitionRegistry.registerBeanDefinition("engine1", engine1.getBeanDefinition());
        beanDefinitionRegistry.registerBeanDefinition("window1", window1.getBeanDefinition());
        beanDefinitionRegistry.registerBeanDefinition("wheel1", wheel1.getBeanDefinition());

        BeanDefinitionBuilder car1 = BeanDefinitionBuilder.genericBeanDefinition(Car.class);
        car1.addPropertyReference("engine", "engine1");
        car1.addPropertyReference("window", "window1");
        car1.addPropertyReference("wheel", "wheel1");
        beanDefinitionRegistry.registerBeanDefinition("car_d", car1.getBeanDefinition());


        {
            BeanDefinitionBuilder c10 = BeanDefinitionBuilder.genericBeanDefinition(Car.class);

            BeanDefinitionBuilder e1 = BeanDefinitionBuilder.genericBeanDefinition(Engine.class);
            e1.addPropertyValue("name", "e1");
            beanDefinitionRegistry.registerBeanDefinition("e1", e1.getBeanDefinition());

            BeanDefinitionBuilder e2 = BeanDefinitionBuilder.genericBeanDefinition(Engine.class);
            e2.addPropertyValue("name", "e2");
            beanDefinitionRegistry.registerBeanDefinition("e2", e2.getBeanDefinition());

            ManagedList<RuntimeBeanNameReference> list = new ManagedList<>();
            list.add(new RuntimeBeanNameReference("e1"));
            list.add(new RuntimeBeanNameReference("e2"));

            c10.addPropertyValue("engineList", list );
//            c10.addPropertyReference("engineList", "e2");
            beanDefinitionRegistry.registerBeanDefinition("c10", c10.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
