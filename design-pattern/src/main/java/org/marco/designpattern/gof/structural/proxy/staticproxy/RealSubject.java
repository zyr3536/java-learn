package org.marco.designpattern.gof.structural.proxy.staticproxy;

public class RealSubject implements Subject {
    @Override
    public void run() {
        System.out.println("run");
    }
}
