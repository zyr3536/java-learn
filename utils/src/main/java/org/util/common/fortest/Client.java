package org.util.common.fortest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        linkListFor();
        arrayListFor();
        arrayListForEach();

    }
    private static void linkListFor() {
        List<Integer> b = new LinkedList<Integer>() {{
            add(1);
            add(2);
        }};
        for (Integer v : b) {
            System.out.println(v);
        }
    }

    private static void arrayListFor() {
        List<Integer> a = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};


        for (Integer v : a){
            System.out.println(v);
        }
    }

    private static void arrayListForEach() {
        List<Integer> a = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        a.forEach(System.out::println);
    }
}
