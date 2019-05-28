package org.util.common.enumtest;

/**
 * @author: zyr
 * @date: 2019/5/7
 * @description: enum test
 */
public class EnumTest {
    public static void main(String[] args) throws Exception {
        System.out.println(Grade.FIRST);
        System.out.println(Grade.FIRST.name());
        System.out.println(Grade.FIRST.ordinal());
        System.out.println(Grade.valueOf("FIRST"));
        Thread.sleep(2000);
    }
}

@SuppressWarnings("all")
enum Grade {
    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int index;

    Grade(int index) {
        this.index = index;
    }
}
