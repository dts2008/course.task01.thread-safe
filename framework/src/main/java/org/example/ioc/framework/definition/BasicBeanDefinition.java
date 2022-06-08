package org.example.ioc.framework.definition;

import lombok.Value;

import java.lang.reflect.Method;

@Value
public class BasicBeanDefinition implements BeanDefinition {
    String id;
    Class beanClass;
    Method factoryMethod;
}
