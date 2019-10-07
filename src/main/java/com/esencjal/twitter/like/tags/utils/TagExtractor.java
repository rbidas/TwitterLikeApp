package com.esencjal.twitter.like.tags.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagExtractor {
    public static List<String> extract(String message) {
        return Arrays
                .stream(message.split(" "))
                .filter(x -> x.startsWith("#"))
                .map(x -> x.substring(1))
                .collect(Collectors.toList());
    }
}
