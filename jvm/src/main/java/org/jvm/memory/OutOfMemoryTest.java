package org.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * test OOM
 */
public class OutOfMemoryTest {

    public static void main(String[] args) {
        OutOfMemoryTest outOfMemoryTest = new OutOfMemoryTest();
        outOfMemoryTest.heapOOM();
    }

    public void heapOOM() {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}
