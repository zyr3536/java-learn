package org.marco.designpattern.gof.behavioral.chain;

public abstract class Handler {
    protected Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    Handler getNext() {
        return handler;
    }

    public abstract void handle(String request);
}
