package org.example.ioc.framework;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(Class clazz) {
        super("Bean not found for class " + clazz.getName());
    }

    public BeanNotFoundException(String id) {
        super("Bean not found for id " + id);
    }
}
