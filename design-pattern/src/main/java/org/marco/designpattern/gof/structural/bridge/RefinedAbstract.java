package org.marco.designpattern.gof.structural.bridge;

public class RefinedAbstract extends Abstarction {
    public RefinedAbstract(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("refined abstract");
        implementor.operation();
    }
}
