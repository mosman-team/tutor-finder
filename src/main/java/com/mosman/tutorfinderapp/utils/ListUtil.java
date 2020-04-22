package com.mosman.tutorfinderapp.utils;

import java.util.*;

public class ListUtil {
    public static int UnionSize(List<String> strArr1, List<String> strArr2) {
        Set<String> result = new HashSet<String>(strArr1);

        result.retainAll(strArr2);
        return result.size();
    }

    public static void main(String[] args) {

        List<String> l1 = Arrays.asList("hello", "world", "two");
        List<String> l2 = Arrays.asList("world", "again");

        int resSetSize = UnionSize(l1, l2);

        System.out.println(resSetSize);

    }
}
