package org.marco.designpattern.gof.behavioral.vistor;

public interface Element {
    void accept(Vistor vistor);
    void operate();
}
