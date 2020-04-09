package kits.bioinfo.util;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class CollectionsUtil {

    @SafeVarargs
    public static <T> List<T> concat(List<T> ... lists) {
        return Stream.of(lists).flatMap(x -> x.stream()).collect(toList());
    }
    
}
