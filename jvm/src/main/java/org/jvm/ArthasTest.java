package org.jvm;

/**
 * 学习如何使用arthas
 */
public class ArthasTest {

    public static void main(String[] args) throws Exception {
        ArthasTest client = new ArthasTest();
        while (true) {
            System.out.println(client.fun());
            Thread.sleep(1000);
        }
    }

    public int fun() {
        return 0;
    }
}
