package com.example.dataxm.utils;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ConfigTool {

    public static void addFilterToPredicate(List<String> predicates, String path, Object filter) {
        if (Objects.nonNull(filter) && filter != "") predicates.add(path);
    }

    public static String validateNotNullReturn(Object data, String returnNull){
        if(data != null) return data.toString();
        return returnNull;
    }

    public static String getMonthName(int monthNumber) {
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es"));
        return dfs.getMonths()[monthNumber - 1].toUpperCase();
    }

    public static Integer getTotalPages(Long totalRows, int size){
        return Math.toIntExact(totalRows % size == 0 ? (totalRows / size) : (totalRows / size) + 1);
    }
}
