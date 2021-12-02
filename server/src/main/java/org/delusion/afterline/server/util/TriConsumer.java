package org.delusion.afterline.server.util;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.stream.Collectors;

@FunctionalInterface
public interface TriConsumer<T1, T2, T3> {

    void accept(T1 a, T2 b, T3 c);

}
