package com.huotu.test;

import com.huotu.test.Interface.Montage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mensonges on 2017/5/18.
 * 对Lambda表达式的大致解析
 */
public class LambdaTest {

    @org.junit.Test
    public void test1(){
        final String s1 =";";
        Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e +s1) );
        Arrays.asList( "a", "b", "d" ).sort((e1,e2) ->e1.compareTo(e2));
    }


    public interface Function{
        default String defaultMethod() {
            System.out.println("1");
            return "hello java8";
        }

    }

    public interface testFunction{
        static Function create(Supplier<Function> supplier){
                return supplier.get();
        }
    }

    private static class testImpl implements  Function{

    }

    private static class DefaultImpl implements Function{
        @Override
        public String defaultMethod(){
            System.out.println("2");
            return "java8 is amazing";
        }
    }

    @org.junit.Test
    public void test2(){
        /** 默认方法和静态方法的区别 */
        Function function = testFunction.create(testImpl::new);
        System.out.println(function.defaultMethod());
        function = testFunction.create(DefaultImpl::new);
        System.out.println(function.defaultMethod());

        /** 构造方法引用 */
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList( car );
        /** 静态方法引用 */
        cars.forEach(Car::collide);
        /** 类实例引用 */
        cars.forEach(Car::repair);
        /** 引用特殊类 */
        final Car ploice = Car.create(Car::new);
        cars.forEach(ploice::follow);
        System.out.println("Hello World");
    }


    public static class Car {
        public static Car create( final Supplier< Car > supplier ) {
            return supplier.get();
        }

        @org.junit.Test
        public static void collide( final Car car ) {
            System.out.println( "Collided " + car.toString() );
        }

        @org.junit.Test
        public void follow( final Car another ) {
            System.out.println( "Following the " + another.toString() );
        }

        @org.junit.Test
        public void repair() {
            System.out.println( "Repaired " + this.toString() );
            Predicate<Integer> Least5 = x -> x>5;
            System.out.println(Least5);
        }

        /**
         *
         * 这里需要注意的是
         * （1）:Lambda表达式是闭包 引用的是值 而不是变量
         * （2）:Lambda更多的是根据上下文推断参数类型
         * （3）:局部变量放到Lambda中访问，默认是final修饰的
         *  Lambda本质是函数式接口，只有一个抽象方法的接口。用@FunctionInterface表示区别
         *  (4):Lambda本质上是一个匿名方法，将行为像数据一样传递
         *  (5):lambda表达式的组成 实际上 由 形参 + -> + 方法体(表达式或者代码块);
         *  如下例子的Test6  montage实际上是实现 Montage接口方法的子类对象
         *
          */

        /* 空参 返回类型void */
        Runnable test1 = () -> System.out.println("hello Lambda");
        /* 一个参数 参数括号可以省略 */


        /* Lambda 也可以使用{}将代码括起 */
        Runnable multiStatement = () ->{
            System.out.println("hello Lambda");
            System.out.println("hello java8");
        };


    }

    @org.junit.Test
    public void test6(){
        Montage montage = message -> System.out.println("hello Lambda"+message);
        montage.test("Stream");
    }
    /* 创建add函数，计算X+y的结果 参数类型可以省略 */

    BinaryOperator<Long> addTest = (Long x,Long y) -> x+y;

    @org.junit.Test
    public void test3(){
        Predicate<Integer> Least5 = x -> x>5;
        System.out.println(Least5.test(7)); //true
        BinaryOperator<Integer> add = (x,y) -> x+y;
        System.out.println(add.apply(3,10));

        /* foreach循环 */
        int count = 0;
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(3);
        for (Integer s1: list) {
            if(s1>5){
                count++;
            }
        }

        List<String> collected = Stream.of("a","b").map(String -> String.toUpperCase()).collect(Collectors.toList());
        System.out.println(collected);
    }



}


