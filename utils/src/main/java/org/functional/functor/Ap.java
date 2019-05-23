package org.functional.functor;

import java.util.function.Function;

public class Ap<T> implements Applicative<T> {
    private final T value;

    public Ap(T value) {
        this.value = value;
    }

    @Override
    public Applicative<T> ap(Functor1<T> functor) {

        return null;
    }

    @Override
    public <R> Functor1<R> map(Function<T, R> f) {
        return null;
    }

    @Override
    public <R> Functor1<R> of(T t) {
        return null;
    }
}
