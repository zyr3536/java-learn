package org.marco.designpattern.gof.structural.facade;

public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.compose();
    }
}
