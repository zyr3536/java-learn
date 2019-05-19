package org.functional.monad;

import org.functional.functor.Functor;

import java.util.function.Function;


/**
 * Monad是一个Functor
 * Monad的核心是flatMap，解决嵌套包装问题
 */
public interface Monad<T, M extends Monad<?,?>> extends Functor<T, M> {
    M flatMap(Function<T, M> f);
}
