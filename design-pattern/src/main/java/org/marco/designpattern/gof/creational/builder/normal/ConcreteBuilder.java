package org.marco.designpattern.gof.creational.builder.normal;

public class ConcreteBuilder extends Builder {

    @Override
    protected void buildPartA() {
        product.setPartA("A");
    }

    @Override
    protected void buildPartB() {
        product.setPartB("B");
    }

    @Override
    protected void buildPartC() {
        product.setPartC("C");
    }
}
