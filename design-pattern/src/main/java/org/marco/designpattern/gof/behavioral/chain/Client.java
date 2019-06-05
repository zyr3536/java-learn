package org.marco.designpattern.gof.behavioral.chain;

public class Client {
    public static void main(String[] args) {
        Handler firstHandler = new FirstHandler();
        Handler secondHandler = new SecondHandler();
        firstHandler.setHandler(secondHandler);
        firstHandler.handle("1");
    }
}
