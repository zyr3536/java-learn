package org.functional.functor;

import java.util.function.Function;

/**
 * Functor1（函子）是一种数据结构，能够封装一些址
 * Functor会接受封装了值的函子，对其进行转换，最后包装成另一个函子
 */
public interface Functor1<T> {
    <R> Functor1<R> map(Function<T, R> f);
}

