package org.marco.designpattern.gof.structural.bridge;

public abstract class Abstarction {
    protected Implementor implementor;

    public Abstarction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
