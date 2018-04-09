package project.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConverterHelper {

    public static <S, T> List<T> convert(List<S> source, Function<S, T> mapper) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        List<T> target = source
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

        return target;
    }
}

