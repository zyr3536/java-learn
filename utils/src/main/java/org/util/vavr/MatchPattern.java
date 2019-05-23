package org.util.vavr;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

import io.vavr.API;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.control.Try;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.time.LocalDate;

/**
 * vavr 模式匹配
 */
public class MatchPattern {
    public static void main(String[] args) {
        val client = new MatchPattern();
//        client.simpleTest();
//        client.sideAffectTest();
    }

    public void simpleTest() {
        int input = 3;
        String output = Match(input).of(
                Case($(1), "yi"),
                Case($(2), "er"),
                Case($(), "default")
        );
        System.out.println(output);
    }

    public void sideAffectTest() {
        int input = 3;
        Match(input).of(
                Case($(isIn(1,2)),o -> run(() -> System.out.println("1 or 2"))),
                Case($(3), o -> run(() -> System.out.println("3"))),
                Case($(), o -> run(() -> System.out.println("default")))
        );
    }

    public void simpleTest2() {
        int year = 1990;
        String s = Match(year).of(
                Case($(noneOf(isIn(1990, 1991, 1992), is(1995))),"age match"),
                        Case($(), "no age match")
        );
        System.out.println(s);
    }

   /* public void complexTest() {
        val employee = new Employee("zyr", "23");

        String result = Match(employee).of(
                Case(Employee($("Carl"), $()),
                        (name, id) -> "Carl has employee id "+id),
                Case($(),
                        () -> "not found"));
    }*/

//    public void complexTest2() {
//        LocalDate date = LocalDate.of(2017, 2, 13);
//
//        String result = Match(date).of(
//                Case(InnerPatterns.$LocalDate($(2016), $(3), $(13)),
//                        () -> "2016-02-13"),
//                Case(InnerPatterns.$LocalDate($(2016), $(), $()),
//                        (y, m, d) -> "month " + m + " in 2016"),
//                Case(InnerPatterns.$LocalDate($(), $(), $()),
//                        (y, m, d) -> "month " + m + " in " + y),
//                Case($(),
//                        () -> "(catch all)")
//        );
//        System.out.println(result);
//    }

}
