package org.example.ioc.framework;

import java.util.List;

public interface BeanFactory {
    Object getBean(String id);
    <T> T getBean(Class<T> clazz);
    <T> List<T> getBeans(Class<T> clazz);
    boolean containsBean(String id);
}
