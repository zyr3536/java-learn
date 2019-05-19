package org.functional.functor;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 自己实现一个list函子
 */
public class Flist<T> implements Functor1<T> {

    private final ImmutableList<T> values;

    @Override
    public <R> Functor1<R> map(Function<T, R> f) {
        if (values == null) {
            return new Flist<>(null);
        } else {
            List<R> tmpList = new ArrayList<>(values.size());
            for (T value : values) {
                tmpList.add(f.apply(value));
            }
            return new Flist<>(ImmutableList.copyOf(tmpList));
        }
    }

    public Flist(ImmutableList<T> values) {
        this.values = values;
    }

    public List<T> get() {
        return values;
    }
}

