package org.marco.designpattern.gof.behavioral.observer;

public class Client {


    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer observer = new ConcreteObserver();
        subject.attach(observer);
        subject.update();
        subject.update();
        subject.detach(observer);
        subject.update();
    }
}
