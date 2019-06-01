package org.marco.designpattern.gof.creational.factory.abstractfactory;

public class Client {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory();
        factory.createProductA().doA();
        factory.createProductB().doB();
    }
}
