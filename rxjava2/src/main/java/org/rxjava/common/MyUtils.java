package org.rxjava.common;

/**
 * @author: zyr
 * @date: 2019/4/30
 * @description: 工具类
 */
public class MyUtils {

    /**
     * sleep ms
     * @param ms
     */
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
