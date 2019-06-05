package org.marco.designpattern.gof.behavioral.chain;

public class FirstHandler extends Handler {
    @Override
    public void setHandler(Handler handler) {
        super.setHandler(handler);
    }

    @Override
    Handler getNext() {
        return super.getNext();
    }

    @Override
    public void handle(String request) {
        if (request.equals("11")) {
            System.out.println("first handler");
        } else {
            getNext().handle(request);
        }
    }
}
