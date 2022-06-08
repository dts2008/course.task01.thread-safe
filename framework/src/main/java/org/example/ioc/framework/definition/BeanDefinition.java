package org.example.ioc.framework.definition;

import java.lang.reflect.Method;

public interface BeanDefinition {
    String getId();
    Class getBeanClass();
    Method getFactoryMethod();
}
