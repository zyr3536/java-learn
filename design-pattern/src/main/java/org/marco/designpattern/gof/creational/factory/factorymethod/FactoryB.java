package org.marco.designpattern.gof.creational.factory.factorymethod;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 工厂类B
 */
public class FactoryB implements IFactory {
    @Override public IProduct createProduct() {
        return new ProductB();
    }
}
