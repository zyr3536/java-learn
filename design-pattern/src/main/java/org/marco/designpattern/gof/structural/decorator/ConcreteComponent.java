package org.marco.designpattern.gof.structural.decorator;

public class ConcreteComponent implements  Component {
    @Override
    public void operate() {
        System.out.println("component");
    }
}

