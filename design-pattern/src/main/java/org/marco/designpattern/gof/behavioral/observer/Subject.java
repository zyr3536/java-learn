package org.marco.designpattern.gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private final List<Observer> list = new ArrayList<>();

    /**
     * 添加观察者
     */
    protected void attach(Observer o) {
        list.add(o);
    }

    /**
     * 去掉观察者
     */
    protected void detach(Observer o) {
        list.remove(o);
    }

    public void update() {
        list.forEach(Observer::update);
    }


}
