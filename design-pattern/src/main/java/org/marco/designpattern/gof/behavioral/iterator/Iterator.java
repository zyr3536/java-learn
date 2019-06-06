package org.marco.designpattern.gof.behavioral.iterator;

/**
 * @author: zyr
 * @date: 2019/6/6
 * @description: todo
 */
public interface Iterator<E> {
    boolean hasNext();

    E next();
}
