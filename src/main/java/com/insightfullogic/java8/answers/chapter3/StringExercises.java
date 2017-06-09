package com.insightfullogic.java8.answers.chapter3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StringExercises {

    /**
     * 计算字符串中小写字母个数
     *
     * @param string 字符串
     * @return count 数量
     */
    public static int countLowercaseLetters(String string) {
                return (int)string.chars()
                                .filter(Character::isLowerCase)
                                .count();

    }

    /**
     * 字符串集合中 找出最多的小写字母的字符串
     *
     * @param strings 字符串集合
     * @return
     */
    public static Optional<String> mostLowercaseString(List<String> strings) {
       return strings.stream()
                        .max(Comparator.comparing(StringExercises::countLowercaseLetters));
        /**
         * 1.开启流
         * 2.找出最大值
         * 3.比较排序
         * 4.计算小写字符个数
         * 5.返回小写字母最多的字符串
         */
    }

}
