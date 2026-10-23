package com.techcrack.bookwise.helper;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CollectionHelper {
    public <K, V> Map<K, V> convertListIntoMap(List<V> source, Function<V, K> key) {
        if (source == null)
            return new HashMap<>();

        return source
                .stream()
                .collect(
                        Collectors.toMap(key, value -> value)
                );
    }
}
