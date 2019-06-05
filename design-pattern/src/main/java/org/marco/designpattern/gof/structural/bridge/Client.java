package org.marco.designpattern.gof.structural.bridge;

public class Client {
    public static void main(String[] args) {
        Abstarction abstarction = new RefinedAbstract(new ConcreteImplementor());
        abstarction.operation();
    }
}
