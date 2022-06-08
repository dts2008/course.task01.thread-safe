package org.example.ioc.framework;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.ioc.framework.definition.BeanDefinition;
import org.example.ioc.framework.definition.BeanDefinitionRegistry;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DefaultBeanFactory implements BeanFactory {

    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final Map<String, Object> beans = new HashMap<>();

    @Override
    public Object getBean(String id) {
        if (beans.containsKey(id)) {
            return beans.get(id);
        }

        return createBean(id);
    }


    @Override
    public <T> T getBean(Class<T> clazz) {
        List<BeanDefinition> candidates = beanDefinitionRegistry.getBeanDefinition(clazz);
        if (candidates.size() == 0) {
            throw new BeanNotFoundException(clazz);
        }
        if (candidates.size() > 1) {
            throw new MultipleBeanException(clazz);
        }
        return (T) getBean(candidates.get(0).getId());
    }

    @Override
    public <T> List<T> getBeans(Class<T> clazz) {
        return beanDefinitionRegistry.getBeanDefinition(clazz)
                .stream()
                .map(BeanDefinition::getId)
                .map(this::getBean)
                .map(bean -> (T) bean)
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsBean(String id) {
        return beans.containsKey(id);
    }

    private Object createBean(String id) {

        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(id);
        Object bean = beanDefinition.getFactoryMethod() != null
                ? createByFactoryMerthod(beanDefinition)
                : createByConstructor(beanDefinition);

        beans.put(beanDefinition.getId(), bean);
        return bean;
    }


    private Object createByConstructor(BeanDefinition beanDefinition) {
        Constructor[] constructors = beanDefinition.getBeanClass().getConstructors();
        if (constructors.length != 1) throw new RuntimeException("Bean class must contains only 1 constructor");
        Parameter[] argumentTypes = constructors[0].getParameters();
        Object[] arguments = resolveArguments(argumentTypes);
        try {
            return constructors[0].newInstance(arguments);
        } catch (Exception ignored) {
            return null;
        }
    }


    private Object createByFactoryMerthod(BeanDefinition beanDefinition) {
        Method factoryMethod = beanDefinition.getFactoryMethod();
        Class factoryClass = factoryMethod.getDeclaringClass();
        Object factory = getBean(factoryClass);
        Parameter[] parameters = factoryMethod.getParameters();
        Object[] arguments = resolveArguments(parameters);
        try {
            return factoryMethod.invoke(factory, arguments);
        } catch (Exception ignored) {
            return null;
        }
    }

    @SneakyThrows
    private Object[] resolveArguments(Parameter[] parameters) {
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if(parameters[i].getType().isAssignableFrom(List.class)){
                Type type = ((ParameterizedType)parameters[i].getParameterizedType()).getActualTypeArguments()[0];
                Class<?> clazz = Class.forName(type.getTypeName());
                List<?> beans = getBeans(clazz);
                args[i] = beans;
            }else {
                args[i] = getBean(parameters[i].getType());
            }
        }
        return args;
    }
}
