package org.marco.designpattern.gof.creational.builder.normal;

public abstract class Builder {
    protected Product product = new Product();

    public Builder() {

    }

    protected abstract void buildPartA();
    protected abstract void buildPartB();
    protected abstract void buildPartC();

    public final Product getResult() {
        return product;
    }
}
