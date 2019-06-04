package org.marco.designpattern.gof.structural.adapter;

/**
 * @author: zyr
 * @date: 2019/6/4
 * @description: todo
 */
public class Client {
    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.input("1");
        System.out.println("===================");
        Adapter adapter = new Adapter(new Adaptee());
        adapter.input("1");
    }
}
