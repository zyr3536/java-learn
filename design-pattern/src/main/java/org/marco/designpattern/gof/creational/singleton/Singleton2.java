package org.marco.designpattern.gof.creational.singleton;

/**
 * DCL，懒汉
 */
public class Singleton2 {
    private static volatile Singleton2 INSTANCE;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}
