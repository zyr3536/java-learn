package org.marco.designpattern.gof.structural.facade;

public class Facade {
    private PartA partA = new PartA();
    private PartB partB = new PartB();
    private PartC partC = new PartC();

    public void compose() {
        partA.compose();
        partB.compose();
        partC.compose();
    }
}
