package org.util.common.bittest;

import lombok.val;

import java.util.Scanner;

/**
 * 比特运算技巧
 */
public class Client {
    public static void main(String[] args) {
        val client = new Client();
//        client.test1();
        client.test2();
//        client.test3();
    }

    /**
     * 异或交换数字，不需要中间变量
     */
    public void test1() {
        System.out.println("请输入两个数：");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个数：");
        int x = scanner.nextInt();
        System.out.println("请输入第二个数：");
        int y = scanner.nextInt();
        scanner.close();
        x ^= y;
        y ^= x;
        x ^= y;
        System.out.println(String.format("交换之后两个数为:%s,%s", x, y));
    }

    /**
     * 判断两个数是否符号相反
     */
    public void test2() {
        int x = 1;
        int y = 2;

        System.out.println((x ^ y) < 0);
    }

    /**
     * 是否为2的次方
     */
    public void test3() {
        int x = 8;
        System.out.println((x & (x - 1)) == 0);
    }
}
