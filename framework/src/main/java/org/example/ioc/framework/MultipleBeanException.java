package org.example.ioc.framework;

public class MultipleBeanException extends RuntimeException {

    public MultipleBeanException(Class clazz) {
        super("More 1 candidates for class " + clazz.getName());
    }
}
