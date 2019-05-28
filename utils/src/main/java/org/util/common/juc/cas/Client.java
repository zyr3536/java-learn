package org.util.common.juc.cas;

import lombok.val;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    public static void main(String[] args) throws Exception {
        val client = new Client();
        client.test1();
    }

    public void test1() throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; ++i) {
            new Thread(() -> {
                int oldV = atomicInteger.get();
                int newV = oldV + 1;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行结果：" + atomicInteger.compareAndSet(oldV, newV));
                System.out.println(String.format("执行加1操作，结果为:%s", atomicInteger.get()));
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }
}
