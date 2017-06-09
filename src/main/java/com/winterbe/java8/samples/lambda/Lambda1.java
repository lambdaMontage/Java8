package com.winterbe.java8.samples.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author montage
 */
public class Lambda1 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        /** 比较器比较集合数据  */
       Collections.sort(names, new Comparator<String>() {
           @Override
           public int compare(String a, String b) {
               return a.compareTo(b);
           }
       });

       /** Lambda表达式变形 */
        Collections.sort(names,(String a, String b) -> {return a.compareTo(b);});
        Collections.sort(names,(String a, String b) -> a.compareTo(b));
        Collections.sort(names,(a,b) -> a.compareTo(b));

        System.out.println(names);

        /** 反向排序 */
        names.sort(Collections.reverseOrder());

        System.out.println(names);

        /** 返回一个空的友好比较器认为 null要大于非空。  */
        List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        List<String> names3 = null;

        /** 返回一个比较器，比较 Comparable对象自然秩序。  */
        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));

        System.out.println(names3);
    }

}