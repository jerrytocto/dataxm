package com.example.dataxm.utils;

import java.util.List;
import java.util.Objects;

public class ConfigTool {

    public static List<String> addFilterToPredicate(List<String> predicates, String path, Object filter) {
        if (Objects.nonNull(filter) && filter != "") predicates.add(path);
        return predicates;
    }
}
