package org.marco.designpattern.gof.behavioral.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zyr
 * @date: 2019/6/6
 * @description: todo
 */
public class MyList<E> implements Iteratable {
    final List<E> myList;

    public MyList(E... elements) {
        myList = Arrays.asList(elements);
    }

    @Override public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        private volatile int currentIndex = 0;

        @Override public boolean hasNext() {
            return currentIndex < myList.size();
        }

        @Override public synchronized E next() {
            return myList.get(currentIndex++);
        }
    }
}
