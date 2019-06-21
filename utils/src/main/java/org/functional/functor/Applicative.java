package org.functional.functor;

public interface Applicative<T> extends Functor1<T> {
    public Applicative<T> ap(Functor1<T> functor);
}
