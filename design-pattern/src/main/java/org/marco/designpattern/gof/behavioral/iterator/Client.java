package org.marco.designpattern.gof.behavioral.iterator;

/**
 * @author: zyr
 * @date: 2019/6/6
 * @description: todo
 */
public class Client {
    public static void main(String[] args) {
        MyList myList = new MyList(1, 2, 3, 4, 5, 6);
        Iterator iterator = myList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
