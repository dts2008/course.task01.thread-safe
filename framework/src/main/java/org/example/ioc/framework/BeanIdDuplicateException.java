package org.example.ioc.framework;

public class BeanIdDuplicateException extends RuntimeException {
    public BeanIdDuplicateException(String id) {
        super("Bean with id "+id+" defined more then 1 times");
    }
}
