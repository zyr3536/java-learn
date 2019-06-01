package org.marco.designpattern.gof.behavioral.templatemethod;

public class ConcreteProcess extends Process {
    @Override
    protected void step1() {
        System.out.println("step1");
    }

    @Override
    protected void step2() {
        System.out.println("step2");
    }

    @Override
    protected void step3() {

    }


    @Override
    protected boolean needStep3() {
        return false;
    }
}
