package org.marco.designpattern.gof.behavioral.strategy;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setStrategy(new ConcreteStrategy1());
        context.algorithm();
        context.setStrategy(new ConcreteStrategy2());
        context.algorithm();
    }
}
