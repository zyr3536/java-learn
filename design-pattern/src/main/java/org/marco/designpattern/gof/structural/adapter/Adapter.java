package org.marco.designpattern.gof.structural.adapter;

/**
 * @author: zyr
 * @date: 2019/6/4
 * @description: todo
 */
public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override public void input(String s) {
        System.out.println("开始适配");
        adaptee.input(Integer.parseInt(s));
    }
}
