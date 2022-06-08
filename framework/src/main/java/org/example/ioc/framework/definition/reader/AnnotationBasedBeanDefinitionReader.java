package org.example.ioc.framework.definition.reader;

import lombok.RequiredArgsConstructor;
import org.example.ioc.framework.definition.BasicBeanDefinition;
import org.example.ioc.framework.definition.BeanDefinition;
import org.example.ioc.framework.util.ReflectUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationBasedBeanDefinitionReader implements BeanDefinitionReader{
    private final String packageName;
    private final Class<? extends Annotation>[] annotations;

    public AnnotationBasedBeanDefinitionReader(String packageName, Class<? extends Annotation> ...annotations) {
        this.packageName = packageName;
        this.annotations = annotations;
    }

    @Override
    public List<BeanDefinition> read() {
        return ReflectUtils.scanPackage(packageName).stream()
                .filter(clazz-> Arrays.stream(annotations).anyMatch(clazz::isAnnotationPresent))
                .map(clazz-> new BasicBeanDefinition(clazz.getSimpleName(),clazz,null))
                .collect(Collectors.toList());
    }
}
