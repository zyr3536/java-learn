package org.marco.designpattern.gof.structural.decorator;

public class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        component.operate();
        System.out.println("decorate2");
    }
}
