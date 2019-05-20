package org.marco.designpattern.gof.structural.decorator;

public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        component.operate();
        System.out.println("decorate1");
    }
}
