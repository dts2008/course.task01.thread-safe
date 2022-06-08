package org.example.ioc.framework.definition.reader;

import org.example.ioc.framework.annotation.Bean;
import org.example.ioc.framework.annotation.Configuration;
import org.example.ioc.framework.definition.BasicBeanDefinition;
import org.example.ioc.framework.definition.BeanDefinition;
import org.example.ioc.framework.util.ReflectUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigurationBasedBeanDefinitionReader implements BeanDefinitionReader{
    private final String packageName;

    public ConfigurationBasedBeanDefinitionReader( String packageName, Class<? extends Annotation> ...annotations) {
        this.packageName = packageName;
    }
    
    @Override
    public List<BeanDefinition> read() {
        return ReflectUtils.scanPackage(packageName).stream()
                .filter(clazz-> clazz.isAnnotationPresent(Configuration.class))
                .map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(method -> method.isAnnotationPresent(Bean.class))
                .map(method-> new BasicBeanDefinition(method.getName(),method.getReturnType(),method))
                .collect(Collectors.toList());
    }
}
