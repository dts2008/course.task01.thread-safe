package org.example.ioc.framework.definition;

import java.util.List;
import java.util.Set;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String id);
    List<BeanDefinition> getBeanDefinition(Class clazz);
    boolean hasBeanDefinition(String id);
    Set<String> getBeanDefinitionIds();
}
