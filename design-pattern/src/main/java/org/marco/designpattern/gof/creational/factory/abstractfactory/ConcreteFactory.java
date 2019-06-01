package org.marco.designpattern.gof.creational.factory.abstractfactory;

public class ConcreteFactory extends AbstractFactory {
    @Override
    public IProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    public IProductB createProductB() {
        return new ConcreteProductB();
    }
}
