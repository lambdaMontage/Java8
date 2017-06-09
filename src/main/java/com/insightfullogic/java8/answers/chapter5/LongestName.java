package com.insightfullogic.java8.answers.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class LongestName {
    private static final  Comparator<Artist> byNameLength = comparing(artist -> artist.getName().length());

    /**
     * 找出名字最长的艺术家 reduce高阶函数版
     * @param artists 集合
     * @return
     */
    public static Artist byReduce(List<Artist> artists) {
        return artists.stream()
                      .reduce((acc, artist) -> {
                          return (byNameLength.compare(acc, artist) >= 0) ? acc : artist;
                      })
                      .orElseThrow(RuntimeException::new);
    }

    /**
     *
     * 找出名字最长的艺术家 收集器版
     * @param artists 集合
     * @return
     */
    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream()
                      .collect(Collectors.maxBy(byNameLength))
                      .orElseThrow(RuntimeException::new);
    }

}
