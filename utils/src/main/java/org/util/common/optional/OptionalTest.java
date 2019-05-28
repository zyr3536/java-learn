package org.util.common.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @author: zyr
 * @date: 2019/5/13
 * @description: todo
 */
public class OptionalTest {
    public static void main(String[] args) {
        A a = new A();
        a.setB(new B());
        boolean flag = Optional.ofNullable(a).map(A::getB).map(B::getC).map(C::getD).isPresent();
        System.out.println(flag);
    }

    @Data
    static class A {
        B B;
    }
    @Data
    static class B {
        C c;
    }
    @Data
    static class C {
        D d;
    }


    @Data
    static class D {
        int d;
    }
}
