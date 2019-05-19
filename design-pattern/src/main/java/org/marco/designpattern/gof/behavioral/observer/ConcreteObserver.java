package org.marco.designpattern.gof.behavioral.observer;

public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("update!");
    }
}
