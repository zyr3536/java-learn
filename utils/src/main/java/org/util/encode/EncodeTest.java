package org.util.encode;

import java.net.URLEncoder;

/**
 * @author: zyr
 * @date: 2019/5/21
 * @description: todo
 */
public class EncodeTest {
    public static void main(String[] args) throws Exception {
        String s1 = URLEncoder.encode("ʧ", "utf8");
        System.out.println(s1);
        String s2 = URLEncoder.encode("失", "GBK");
        System.out.println(s2);
    }
}
