package org.util.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author: zyr
 * @date: 2019/5/16
 * @description: todo
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        CompletableFutureTest client = new CompletableFutureTest();
        client.test1();
    }

    public void test1() throws Exception {
        // 第一种
        CompletableFuture completableFuture = CompletableFuture.completedFuture("complete");
        System.out.println("是否取消:" + completableFuture.isCancelled());
        System.out.println("是否完成:" + completableFuture.isDone());
        System.out.println("是否出现异常:" + completableFuture.isCompletedExceptionally());
        System.out.println("======================");
        // 第二种
        completableFuture = new CompletableFuture();
        completableFuture.complete("ok!");
        completableFuture.cancel(true);
        completableFuture.completeExceptionally(new RuntimeException("aa"));
        System.out.println("是否取消:" + completableFuture.isCancelled());
        System.out.println("是否完成:" + completableFuture.isDone());
        System.out.println("是否出现异常:" + completableFuture.isCompletedExceptionally());
        // get 会阻塞
        System.out.println(completableFuture.get());

    }
}
