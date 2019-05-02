package org.jvm.memory;

/**
 * 测试 {@link String#intern()}方法
 */
public class InternTest {
    public static void main(String[] args) throws Exception {
        String str1 = new StringBuilder("c").append("c").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
