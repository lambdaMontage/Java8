package com.insightfullogic.java8.answers.chapter6;

import java.util.List;

public class BuggyReduce {

    /*
    Buggy Version:
    // BEGIN buggyMultiplyThrough
public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
    return linkedListOfNumbers.stream()
                  .reduce(5, (acc, x) -> x * acc);
}
    // END buggyMultiplyThrough
    */

    /**
     * 集合中的数字相乘，再乘以5
     *
     * @param linkListOfNumbers 整数型集合
     * @return
     */

    public static int multiply(List<Integer> linkListOfNumbers){
        return  linkListOfNumbers.stream()
                                    .reduce(5,(x,y) -> x * y);

    }

    /**
     * 并行 集合中的数字相乘 再乘以5
     *
     * @param numbers 整数型集合
     * @return
     */
    public static int multiplyThrough(List<Integer> numbers) {
        return 5 * numbers.parallelStream()
                          .reduce(1, (acc, x) -> x * acc);
    }

}
