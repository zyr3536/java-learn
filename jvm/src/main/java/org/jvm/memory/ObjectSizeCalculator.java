package org.jvm.memory;

import org.apache.lucene.util.RamUsageEstimator;
import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;

/**
 * 查看对象大小
 */
public class ObjectSizeCalculator {

    public static void main(String[] args) throws Exception {
        ObjectSizeCalculator objectSizeCalculator = new ObjectSizeCalculator();
//        objectSizeCalculator.lucene();
        objectSizeCalculator.unsafe();
    }

    /**
     * 利用apache-lucene的{@link RamUsageEstimator}来计算对象占用内存大小
     */
    public void lucene() {
        Object o = new Object();
        // 对象本身大小，8+4字节，java8默认开启了指针压缩，大小为12
        // 可以用  -XX:-UseCompressedOops 关闭指针压缩，此时大小为8+8
        // fixme 但是用这个方法即使开始了指针压缩算到的还是没开启之前的大小，很奇怪
        System.out.println("Object对象的大小是：" + RamUsageEstimator.shallowSizeOf(o));
        long a = RamUsageEstimator.shallowSizeOfInstance(Object.class);
        System.out.println(a);
    }


    /**
     * 通过{@link Unsafe}类可以获取到对象的shallowSize
     */
    public void unsafe() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        long headSize = unsafe.objectFieldOffset(Integer.class.getDeclaredField("value"));
        System.out.println("对象头大小：" + headSize);
    }

    public void instrumentation() {

    }
}
