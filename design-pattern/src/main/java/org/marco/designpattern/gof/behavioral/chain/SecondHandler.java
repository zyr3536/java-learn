package org.marco.designpattern.gof.behavioral.chain;

public class SecondHandler extends Handler {
    @Override
    public void setHandler(Handler handler) {
        super.setHandler(handler);
    }

    @Override
    Handler getNext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void handle(String request) {
        System.out.println("second handler");
    }
}
