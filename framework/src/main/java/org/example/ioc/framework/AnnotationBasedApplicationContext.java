package org.example.ioc.framework;

import java.util.List;

public class AnnotationBasedApplicationContext implements ApplictionContext {
    public AnnotationBasedApplicationContext(Class mainClass) {

    }

    @Override
    public void refresh() {

    }

    @Override
    public Object getBean(String id) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

    @Override
    public <T> List<T> getBeans(Class<T> clazz) {
        return null;
    }

    @Override
    public boolean containsBean(String id) {
        return false;
    }
}
