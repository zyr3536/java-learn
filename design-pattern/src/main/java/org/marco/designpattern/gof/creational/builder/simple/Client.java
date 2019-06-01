package org.marco.designpattern.gof.creational.builder.simple;

public class Client {
    public static void main(String[] args) {
        Family family = Family.builder()
                .father("father")
                .mother("mother")
                .child("child")
                .build();
        System.out.println(family);
    }
}
