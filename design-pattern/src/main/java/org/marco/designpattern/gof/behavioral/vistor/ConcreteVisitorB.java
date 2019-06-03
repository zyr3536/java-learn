package org.marco.designpattern.gof.behavioral.vistor;

public class ConcreteVisitorB implements Vistor {
    @Override
    public void visit(ConcreteElementA concreteElementA) {
        System.out.print("visitA visits ");
        concreteElementA.operate();
    }

    @Override
    public void visit(ConcreteElementB concreteElementB) {
        System.out.print("visitB visits ");
        concreteElementB.operate();
    }
}
