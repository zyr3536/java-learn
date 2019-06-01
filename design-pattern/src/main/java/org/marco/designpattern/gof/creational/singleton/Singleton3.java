package org.marco.designpattern.gof.creational.singleton;

/**
 * 静态内部类，懒汉
 */
public class Singleton3 {
    private Singleton3() {

    }

    public Singleton3 getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * 静态内部类
     */
    private static class SingletonInstance {
        private static final Singleton3 INSTANCE = new Singleton3();
    }
}
