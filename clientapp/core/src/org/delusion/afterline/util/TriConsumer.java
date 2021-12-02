package org.delusion.afterline.util;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.stream.Collectors;

@FunctionalInterface
public interface TriConsumer<T1, T2, T3> {

    void accept(T1 a, T2 b, T3 c);

    default Class<T2> getT() throws ClassNotFoundException {
        return (Class<T2>)Class.forName(Arrays.stream(getClass().getTypeParameters()).collect(Collectors.toList()).get(1).getTypeName());
    }

}
