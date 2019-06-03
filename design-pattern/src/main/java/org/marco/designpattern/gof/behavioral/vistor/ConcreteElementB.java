package org.marco.designpattern.gof.behavioral.vistor;

public class ConcreteElementB implements Element {
    @Override
    public void accept(Vistor vistor) {
        vistor.visit(this);
    }

    @Override
    public void operate() {
        System.out.println("ConcreteElementB");
    }
}
