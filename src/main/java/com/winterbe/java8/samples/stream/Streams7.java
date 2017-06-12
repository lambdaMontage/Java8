package com.winterbe.java8.samples.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * peek flatMap 流式处理
 * @author montage
 */
public class Streams7 {

    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    static void test2() {

        IntStream.range(0, 5)
                .mapToObj(num -> new Foo("Foo" + num))
                //对每个元素执行操作 遍历数据 添加到 bars中
                .peek(f -> IntStream.range(0, 5)
                .mapToObj(num -> new Bar("Bar" + num + "<>" + f.name))
                .forEach(f.bars::add))
                //将List<Bar> bars 转换成流
                .flatMap(f -> f.bars.stream())
                .forEach(c -> System.out.println(c.name));




    }

    static void test1() {
        List<Foo> foos = new ArrayList<>();

        IntStream
            .range(1, 4)
            .forEach(num -> foos.add(new Foo("Foo" + num)));

        foos.forEach(f ->
            IntStream
                .range(1, 4)
                .forEach(num -> f.bars.add(new Bar("Bar" + num + " <- " + f.name))));

        foos.stream()
            .flatMap(f -> f.bars.stream())
            .forEach(b -> System.out.println(b.name));
    }

}