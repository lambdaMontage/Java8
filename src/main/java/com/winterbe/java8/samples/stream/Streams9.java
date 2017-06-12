package com.winterbe.java8.samples.stream;

import java.util.Arrays;

/**
 * @author montage
 */
public class Streams9 {

    /**
     * 集合数据 找出以c开头 转换成大写并排序遍历
     * @param args
     */
    public static void main(String[] args) {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .stream()
                .filter(s ->s.startsWith("c"))
                .map(s -> s.toUpperCase())
                .sorted()
                .forEach(System.out::println);
    }
}
