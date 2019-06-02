package org.marco.designpattern.gof.creational.prototype;

public class Client {
    public static void main(String[] args) throws Exception {
        Product product1 = new Product("a", "b", "c");
        System.out.println("=====clone====");
        Product product2 = product1.clone();
        System.out.println(product2);
    }
}
