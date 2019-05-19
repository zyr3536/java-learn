package org.functional.functor;

import java.util.function.Function;

public class Identity<T> implements Functor<T, Identity<?>> {

    private final T value;

    public Identity(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public <R> Identity<R> map(Function<T, R> f) {
        return new Identity<>(f.apply(value));
    }
}


class Client {
    public static void main(String[] args) {
        String s = "abcd";

        Object value = new Identity<>(s)
                .map(String::toUpperCase)
                .map(s1 -> s1.substring(1))
                .getValue();

        System.out.println(value);

    }
}
