package org.marco.designpattern.gof.behavioral.templatemethod;

public abstract class Process {
    protected abstract void step1();
    protected abstract void step2();
    protected abstract void step3();
    protected abstract boolean needStep3();

    public final void run() {
        step1();
        step2();
        if (needStep3()) {
            step3();
        }
    }
}
