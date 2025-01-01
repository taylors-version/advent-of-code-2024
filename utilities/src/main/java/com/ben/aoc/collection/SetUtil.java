package com.ben.aoc.collection;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUtil {
    public static <T> Set<Pair<T, T>> getPairs(Set<T> set){
        Set<Pair<T, T>> pairs = new HashSet<>();
        List<T> list = new ArrayList<>(set);
        for (int i = 0; i < list .size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Pair<T, T> pair = new Pair<>(list.get(i), list.get(j));
                pairs.add(pair);
            }
        }

        return pairs;
    }
}
