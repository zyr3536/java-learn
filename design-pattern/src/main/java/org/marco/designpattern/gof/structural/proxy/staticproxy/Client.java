package org.marco.designpattern.gof.structural.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.run();
    }
}
