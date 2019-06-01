package org.marco.designpattern.gof.creational.singleton;

/**
 * 枚举，一般是直接拿INSTANCE，getInstance方法可以不提供
 */
public enum Singleton4 {
    INSTANCE;

    public Singleton4 getInstance() {
        return INSTANCE;
    }
}
