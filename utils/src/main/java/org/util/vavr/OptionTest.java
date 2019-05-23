package org.util.vavr;

import io.vavr.control.Option;
import lombok.val;

import java.util.Objects;

/**
 * @author: zyr
 * @date: 2019/5/23
 * @description: option
 */
public class OptionTest {
    public static void main(String[] args) {
        OptionTest client = new OptionTest();
        client.test1();
    }

    public void test1() {
        Integer o = 1;
        val o1 = Option.of(o).map(Integer::doubleValue).flatMap(aDouble -> Option.none()).filter(Objects::nonNull)
                .getOrElseThrow(RuntimeException::new);
        System.out.println(o1);
    }
}
