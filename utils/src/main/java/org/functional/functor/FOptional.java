package org.functional.functor;

import java.util.function.Function;

/**
 * 自己实现一个简单的Optional类
 */
public class FOptional<T> implements Functor1<T> {

    private final T valueOrNull;

    public FOptional(T valueOrNull) {
        this.valueOrNull = valueOrNull;
    }

    public T get() {
        return valueOrNull;
    }

    @Override
    public <R> Functor1<R> map(Function<T, R> f) {
        if (valueOrNull == null) {
            return none();
        } else {
            return new FOptional<>(f.apply(valueOrNull));
        }
    }

    public static <T> FOptional<T> of(T a) {
        return new FOptional<>(a);
    }

    public static <T> FOptional<T> none() {
        return new FOptional<>(null);
    }
}
