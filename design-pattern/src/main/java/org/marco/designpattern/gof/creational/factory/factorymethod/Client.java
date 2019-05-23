package org.marco.designpattern.gof.creational.factory.factorymethod;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 测试类
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new FactoryA();
        IProduct product = factory.createProduct();
        System.out.println(product);
        factory = new FactoryB();
        product = factory.createProduct();
        System.out.println(product);
    }
}
