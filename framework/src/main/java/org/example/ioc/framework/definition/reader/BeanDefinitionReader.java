package org.example.ioc.framework.definition.reader;

import org.example.ioc.framework.definition.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {
    List<BeanDefinition> read();
}
