package org.example.ioc.framework.definition.reader;

import lombok.RequiredArgsConstructor;
import org.example.ioc.framework.definition.BeanDefinition;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CompositeBeanDefinitionReader implements BeanDefinitionReader{
    private final List<BeanDefinitionReader> readers;

    @Override
    public List<BeanDefinition> read() {
        return readers.stream()
                .map(BeanDefinitionReader::read)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
