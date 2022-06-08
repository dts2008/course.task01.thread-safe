package org.example.ioc.framework.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ReflectUtils {

    @SneakyThrows
    public static List<Class> scanPackage(String packageName) {
        //таки ошибся, как раз в jar файле не работало
        //под капотом библиотеки логика аналогичная показанному примеру
        //но имеется ряд классов, позволяющих работать с архивами (jar файл по факту Zip-архив)
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class).stream()
                .collect(Collectors.toList());
    }


}
