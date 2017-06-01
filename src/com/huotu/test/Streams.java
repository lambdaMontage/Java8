package com.huotu.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mensonges on 2017/5/18.
 */
public class Streams {
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
                /** 过滤掉除 开启状态以外的其他数据 */
                .filter(task -> task.getStatus() == Status.OPEN)
                /** 得到Task实例点数 并转成Integer */
                .mapToInt(task ->task.getPoints())
                /** 求和  */
                .sum();
        System.out.println("Total points"+totalPointsOpenTasks);

        /** 查找所有状态的节点 */
       final double totalPointsAll = tasks
               .stream()
               .parallel()
               .map(Task::getPoints)
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
        System.out.println(result);
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
}
