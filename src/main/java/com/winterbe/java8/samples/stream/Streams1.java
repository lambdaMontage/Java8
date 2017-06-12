package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author montage
 */
public class Streams1 {

    public static void main(String[] args) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");


        // filtering
        /**
         * 遍历 集合中a开头的字符串
         */
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        // "aaa2", "aaa1"


        // sorting
        /**
         *
         * 遍历并排序 集合中a开头的字符串
         */
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        // "aaa1", "aaa2"


        // mapping
        /**
         * 集合元素转换成大写，并排序遍历结果
         */
        stringCollection
                .stream()
                .sorted()
                .map((s) ->s.toUpperCase())
                .forEach(System.out::println);


        // matching

        /**
         * 集合中是否包含以 a开头的字符串
         */
        boolean anyStartsWithA = stringCollection
                    .stream()
                    .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true


        /**
         * 集合中所有元素是否 以a开头
         */
        boolean allStartsWithA = stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        /**
         * 集合中是否存在不以 z开头的字符串
         */
        boolean noneStartsWithZ = stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        // counting

        /**
         * 统计集合中以 b开头的字符串个数
         */
        long startsWithB = stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();

        System.out.println(startsWithB);    // 3


        // reducing

        /**
         *聚合函数 集合中的字符串以“#”连接
         */
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"


    }

}
