package org.marco.designpattern.gof.creational.factory.factorymethod;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 工厂类A
 */
public class FactoryA implements IFactory {
    @Override public IProduct createProduct() {
        return new ProductA();
    }
}
