package com.huotu.test;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mensonges on 2017/5/18.
 */
public class StreamTest {
        private enum Status {
            OPEN, CLOSED
        }

        private static final class Task {
            private final Status status;
            private final Integer points;

            Task( final Status status, final Integer points ) {
                this.status = status;
                this.points = points;
            }

            public Integer getPoints() {
                return points;
            }

            public Status getStatus() {
                return status;
            }

            @Override
            public String toString() {
                return String.format( "[%s, %d]", status, points );
            }
        }

    final Collection< Task > tasks = Arrays.asList(
            new Task( Status.OPEN, 5 ),
            new Task( Status.OPEN, 13 ),
            new Task( Status.CLOSED, 8 )
    );

    @org.junit.Test
    public void test1(){
        /** 查找开启状态的节点 */
        final long totalPointsOpenTasks = tasks
                /** 转换为流式处理 */
                .stream()
                /** 得到Status== OPEN的所有数据  */
                .filter(task -> task.getStatus() == Status.OPEN)
                /** 得到Task实例点数 并转成Integer */
                .mapToInt(task ->task.getPoints())
                /** 求和  */
                .sum();
        System.out.println("Total points"+totalPointsOpenTasks);

        /** 查找所有状态的节点 */
       final double totalPointsAll = tasks
               .stream()
               .parallel()      //返回并行等效流 多线程的执行
               .map(Task::getPoints)//将getPoints的值转换为新的流
               .reduce(0,Integer::sum);
        System.out.println("All Total points"+ totalPointsAll);

        /** 根据节点状态对集合分组 */
        final Map<Status,List<Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        final Collection<String> result =tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points/totalPointsAll)   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >
        System.out.println(result); //打印一个list集合
    }

    @org.junit.Test
    public void test2(){
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        /** 默认时区的本地时间 */
        final LocalDateTime datetime = LocalDateTime.now();
        /** 给定clock时区的本地时间 */
        final LocalDateTime datetimeFromClock = LocalDateTime.now( clock );

        System.out.println( datetime );
        System.out.println( datetimeFromClock );
    }

    @org.junit.Test
    public void test3() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javaScript");
        System.out.println(engine.getClass().getName());
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }

    /**
     *
     * 串行流 单线程执行 Stream
     *
     */
    @org.junit.Test
    public void testStream() {
        // 起始时间
        LocalTime start = LocalTime.now();
        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }
        list.stream()// 获取串行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()

        // 终止时间
        LocalTime end = LocalTime.now();
        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
    }

    /**
     * 并行流 多线程执行
     */
    @org.junit.Test
    public void testParallelStream() {
        // 起始时间
        LocalTime start = LocalTime.now();

        List<Integer> list = new ArrayList<>();
        // 将10000-1存入list中
        for (int i = 10000; i >= 1; i--) {
            list.add(i);
        }
        list.parallelStream().sorted().count();
        list.parallelStream()// 获取并行流
                .sorted()// 按自然排序，即按数字从小到大排序
                .count();// count()是终止操作，有终止操作才会执行中间操作sorted()

        // 终止时间
        LocalTime end = LocalTime.now();
        // 时间间隔
        Duration duration = Duration.between(start, end);
        // 输出时间间隔毫秒值
        System.out.println(duration.toMillis());
    }

    /**
     * 使用Stream编程优点如下
     * 减少模板代码 代码语句更加明确
     * 外部迭代变成 内部迭代，方便JVM调优。
     *
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("2one", "two", "three", "4four");
        /** 集合中得到以数字开头的字符串 */
        list.stream()// 1.得到容器的Steam
                .filter(str -> Character.isDigit(str.charAt(0)))// 2.选出以数字开头的字符串
                .forEach(str -> System.out.println(str));// 3.输出字符串
        /** 集合中得到非数字开头字符串，并转换成大写*/
        list.stream()
                .filter(str -> !Character.isDigit(str.charAt(0)))
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}


