package org.example.ioc.framework.definition;

import org.example.ioc.framework.BeanIdDuplicateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DefaultBeanDefinitionRegistry implements BeanDefinitionRegistry{

    // Init all definitions in start of application. Don't to do concurrent
    //private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        if(hasBeanDefinition(beanDefinition.getId())) {
            throw new BeanIdDuplicateException(beanDefinition.getId());
        }
        beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String id) {
        return beanDefinitionMap.get(id);
    }

    @Override
    public List<BeanDefinition> getBeanDefinition(Class clazz) {
        return beanDefinitionMap.values().stream()
                .filter(bd->clazz.isAssignableFrom(bd.getBeanClass()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasBeanDefinition(String id) {
        return beanDefinitionMap.containsKey(id);
    }

    @Override
    public Set<String> getBeanDefinitionIds() {
        return beanDefinitionMap.keySet();
    }
}
