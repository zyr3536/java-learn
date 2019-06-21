package org.util.common.lock.lock1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用unsafe类和volatile语义实现一个锁
 */
public class MyLock {

    /**
     * 标志是否锁住状态
     */
    private volatile int state;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 尾节点
     */
    private Node tail;

    /**
     * state偏移量
     */
    private static long stateOffset;
    /**
     * tail偏移量
     */
    private static long tailOffset;

    static final Node EMPTY = new Node();

    /**
     * unsafe实例
     */
    private static Unsafe unsafe;


    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            stateOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
            tailOffset = unsafe.objectFieldOffset(MyLock.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public MyLock() {
        head = tail = EMPTY;
    }

    public void lock() {
        if (compareAndSetState(0, 1)) {
            return;
        }
        Node node = enqueue();
        Node prev = node.prev;
        // 再次尝试获取锁，需要检测上一个节点是不是head，按入队顺序加锁
        while (node.prev != head || !compareAndSetState(0, 1)) {
            // 未获取到锁，阻塞
            unsafe.park(false, 0L);
        }
        // 下面不需要原子更新，因为同时只有一个线程访问到这里
        // 获取到锁了且上一个节点是head
        // head后移一位
        head = node;
        // 清空当前节点的内容，协助GC
        node.thread = null;
        // 将上一个节点从链表中剔除，协助GC
        node.prev = null;
        prev.next = null;
    }

    public void unlock() {
        state = 0;
        Node next = head.next;
        if (next != null) {
            unsafe.unpark(next.thread);
        }
    }

    private boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private Node enqueue() {
        while (true) {
            Node t = tail;
            Node node = new Node(Thread.currentThread(), t);
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return node;
            }
        }
    }


    /**
     * 用处存放阻塞线程的节点,链表
     */
    private static class Node {
        private Thread thread;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(Thread thread, Node prev) {
            this.thread = thread;
            this.prev = prev;
        }
    }
}
