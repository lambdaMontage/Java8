package com.winterbe.java8.samples.stream;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * 流式处理 容器中的平均数
 * @author montage
 */
public class Streams6 {

    public static void main(String[] args) throws IOException {
        test1();    //5.4
        test2();    //5.5
        test3();    //4.5
        test4();    //5.03
    }

    private static void test4() {
        Stream.of(new BigDecimal("3.6"), new BigDecimal("4.6"),new BigDecimal("6.89"))
                .mapToDouble(s ->s.doubleValue())
                .average()
                .ifPresent(System.out::println);
    }

    /**
     * 索引 0 到 10 也对应整数 0到9 求平均数
     */
    private static void test3() {
        IntStream.range(0,10)
                    .average()
                    .ifPresent(System.out::println);

    }

    private static void test2() {
        IntStream
                .builder()
                .add(2)
                .add(4)
                .add(7)
                .add(9)
                .build()
                .average()
                .ifPresent(System.out::println);

    }

    private static void test1() {
        int[] ints = {1, 3, 5, 7, 11};
        Arrays.stream(ints)
                .average()
                .ifPresent(System.out::println);
    }
}
