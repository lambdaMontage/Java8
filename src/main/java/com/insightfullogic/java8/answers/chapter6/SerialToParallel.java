package com.insightfullogic.java8.answers.chapter6;

import java.util.stream.IntStream;

public class SerialToParallel {


    /**
     * 计算流中元素的平方和
     *
     * @param range
     * @return
     */
    public static int sequentialSumOfSquares(IntStream range) {
        return range.map(x -> x * x)
                    .sum();
    }


    /**
     * 并行计算流中元素的平方和
     * @return
     */
    public static int SumOfSquares(IntStream range){
          return range.parallel()
                    .map(x -> x*x)
                    .sum();
    }


}
