package org.marco.designpattern.gof.structural.decorator;

public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.operate();
        System.out.println("===============");
        Decorator decorate1 = new ConcreteDecorator1(component);
        decorate1.operate();
        System.out.println("===============");
        Decorator decorator2 = new ConcreteDecorator2(new ConcreteDecorator1(component));
        decorator2.operate();

    }
}
