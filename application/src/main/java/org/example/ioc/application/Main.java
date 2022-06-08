package org.example.ioc.application;

import org.example.ioc.application.model.MessagingType;
import org.example.ioc.application.model.User;
import org.example.ioc.application.notification.EmailNotifier;
import org.example.ioc.application.notification.NotifierSelector;
import org.example.ioc.application.notification.PhoneNotifier;
import org.example.ioc.application.order.DefaultOrdersRepository;
import org.example.ioc.application.order.OrderService;
import org.example.ioc.application.order.OrdersRepository;
import org.example.ioc.application.persistance.JdbcTemplate;
import org.example.ioc.framework.AnnotationBasedApplicationContext;
import org.example.ioc.framework.ApplictionContext;
import org.example.ioc.framework.BeanFactory;
import org.example.ioc.framework.DefaultBeanFactory;
import org.example.ioc.framework.annotation.Component;
import org.example.ioc.framework.annotation.Configuration;
import org.example.ioc.framework.definition.BeanDefinitionRegistry;
import org.example.ioc.framework.definition.DefaultBeanDefinitionRegistry;
import org.example.ioc.framework.definition.reader.AnnotationBasedBeanDefinitionReader;
import org.example.ioc.framework.definition.reader.BeanDefinitionReader;
import org.example.ioc.framework.definition.reader.CompositeBeanDefinitionReader;
import org.example.ioc.framework.definition.reader.ConfigurationBasedBeanDefinitionReader;
import org.example.ioc.framework.util.ReflectUtils;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        ApplictionContext context = new AnnotationBasedApplicationContext(Main.class);
//
//        OrderService service = context.getBean(OrderService.class);
//
//        service.makeOrder(
//                new User("one","0000","a@i.ua","123",MessagingType.EMAIL),
//                List.of("p1","p2")
//        );

        String packageName = Main.class.getPackageName();

        BeanDefinitionRegistry registry = new DefaultBeanDefinitionRegistry();

        BeanDefinitionReader reader = new CompositeBeanDefinitionReader(List.of(
                new AnnotationBasedBeanDefinitionReader(packageName,Configuration.class, Component.class),
                new ConfigurationBasedBeanDefinitionReader(packageName)
        ));

        reader.read().forEach(registry::registerBeanDefinition);

        BeanFactory beanFactory = new DefaultBeanFactory(registry);

        OrderService service = beanFactory.getBean(OrderService.class);

        service.makeOrder(
                new User("one","0000","a@i.ua","123",MessagingType.EMAIL),
                List.of("p1","p2")
        );

    }
}
