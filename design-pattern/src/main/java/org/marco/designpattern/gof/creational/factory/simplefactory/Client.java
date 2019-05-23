package org.marco.designpattern.gof.creational.factory.simplefactory;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 测试
 */
public class Client {
    public static void main(String[] args) {
        AbstractProduct product = Factory.newInstance("A");
        product = Factory.newInstance("C");
    }
}
