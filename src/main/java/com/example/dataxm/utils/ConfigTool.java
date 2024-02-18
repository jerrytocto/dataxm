package com.example.dataxm.utils;

import java.util.List;
import java.util.Objects;

public class ConfigTool {

    public static void addFilterToPredicate(List<String> predicates, String path, Object filter) {
        if (Objects.nonNull(filter) && filter != "") predicates.add(path);
    }

    public static String validateNotNullReturn(Object data, String returnNull){
        if(data != null) return data.toString();
        return returnNull;
    }
}
