package org.marco.designpattern.gof.behavioral.templatemethod;

public class Client {
    public static void main(String[] args) {

        Process process = new ConcreteProcess();
        process.run();
    }
}
