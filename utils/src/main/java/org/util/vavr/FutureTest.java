package org.util.vavr;

import io.vavr.CheckedFunction0;
import io.vavr.concurrent.Future;

import java.util.concurrent.TimeUnit;

/**
 * @author: zyr
 * @date: 2019/6/6
 * @description: todo
 */
public class FutureTest {

    private final CheckedFunction0 consumeTimeWork = () -> {
        Thread.sleep(1000);
        return 1;
    };

    private final CheckedFunction0 error = () -> {
        Thread.sleep(1000);
        throw new RuntimeException("error");
    };

    public static void main(String[] args) {
        FutureTest client = new FutureTest();
//        client.test1();
        client.test2();
    }

    public void test1() {
        Future.of(consumeTimeWork).onSuccess(o -> System.out.println("success")).onFailure(throwable -> System.out
                .println(throwable));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void test2() {
        Future.of(consumeTimeWork).await(500, TimeUnit.MILLISECONDS).get();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
