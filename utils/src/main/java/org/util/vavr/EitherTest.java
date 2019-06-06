package org.util.vavr;

import io.vavr.control.Either;
import lombok.val;

/**
 * Either默认使用的是右值
 */
public class EitherTest {
    public static void main(String[] args) {
        EitherTest client = new EitherTest();
//        Client.test1();
        client.test2();
    }

    public void test1() {
        val a = Either.right(1).map(i -> i + 2).getOrElseThrow(() -> new RuntimeException("error"));
        System.out.println(a);
        val c = Either.right(1).filterOrElse(i -> i < 0, i -> 111).getLeft();
        System.out.println(c);
        val b = Either.right(1).swap().get();
        System.out.println(b);
    }

    public void test2() {
        int mark = 85;
        Either either = mark < 85 ? Either.left("mark not acceptable") : Either.right(mark);
        System.out.println(either);
        // then one can do next steps, filter,map,and so on...
    }
}
