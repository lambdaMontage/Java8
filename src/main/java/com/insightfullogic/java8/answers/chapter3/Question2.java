package com.insightfullogic.java8.answers.chapter3;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.List;
import java.util.stream.Stream;

public class Question2 {
    // Q3
    public static int countBandMembersInternal(List<Artist> artists) {

        int totalMembers = 0;
        for (Artist artist: artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }

     // NB: readers haven't learnt about primitives yet, so can't use the sum() method

        /** 对上面循环的lambda变形 */
        return artists.stream()
                        .map(artist -> artist.getMembers().count())
                        .reduce(0L,Long::sum)
                        .intValue();

    }
}
