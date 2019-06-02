package org.marco.designpattern.gof.creational.prototype;

public class Product implements Cloneable{
    String partA;
    String partB;
    String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public Product(String partA, String partB, String partC) {
        this.partA = partA;
        this.partB = partB;
        this.partC = partC;
        System.out.println("Construct!");
    }

    @Override
    public String toString() {
        return "Product{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }

    @Override
    protected Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
