package com.huotu.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by mensonges on 2017/5/18.
 */
public class Test {

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
        }
    }

}
