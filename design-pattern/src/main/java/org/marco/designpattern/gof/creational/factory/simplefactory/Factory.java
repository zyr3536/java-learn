package org.marco.designpattern.gof.creational.factory.simplefactory;

import java.util.NoSuchElementException;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 工厂
 */
public class Factory {
    public static AbstractProduct newInstance(String s) {
        switch (s) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                throw new NoSuchElementException();
        }
    }
}
