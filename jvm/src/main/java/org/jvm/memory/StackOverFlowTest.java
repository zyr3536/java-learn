package org.jvm.memory;

/**
 * stack over flow 测试
 */
public class StackOverFlowTest {

    public static void main(String[] args) {
        StackOverFlowTest s = new StackOverFlowTest();
        s.fun();
    }


    public void fun() {
        fun();
    }
}
