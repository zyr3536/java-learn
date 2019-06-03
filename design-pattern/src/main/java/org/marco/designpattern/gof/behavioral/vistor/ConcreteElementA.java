package org.marco.designpattern.gof.behavioral.vistor;

public class ConcreteElementA implements Element{
    @Override
    public void accept(Vistor vistor) {
        vistor.visit(this);
    }

    @Override
    public void operate() {
        System.out.println("concreteElementA");
    }
}
