package org.marco.designpattern.gof.structural.adapter;

/**
 * @author: zyr
 * @date: 2019/6/4
 * @description: todo
 */
public class ConcreteTarget implements Target {
    @Override public void input(String s) {
        System.out.println("字符串:"+ s);
    }
}
