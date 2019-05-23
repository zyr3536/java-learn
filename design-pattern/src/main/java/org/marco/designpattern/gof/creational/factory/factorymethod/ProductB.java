package org.marco.designpattern.gof.creational.factory.factorymethod;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: 产品B
 */
public class ProductB implements IProduct {
    @Override public IProduct getProduct() {
        return this;
    }
}
