package org.marco.designpattern.gof.structural.bridge;

public class ConcreteImplementor implements Implementor {
    @Override
    public void operation() {
        System.out.println("concrete implementor");
    }
}
