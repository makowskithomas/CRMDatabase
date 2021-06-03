package com.ironhack.utils;

import java.util.List;

public class Utils {


    public static Double getMedian(Integer[] accountList) {
        Double median;
        Integer h1;
        Integer h2;
        if (accountList.length % 2 == 0) {
            h1 = accountList[(accountList.length / 2 - 1)];
            h2 = accountList[(accountList.length / 2)];
            median = (double) (h1 + h2) / 2;
        } else {
            median = (double) accountList[(int) Math.floor(accountList.length / 2)];
        }
        return median;
    }

    public static String objectListToString(List<Object[]> objList){
        String result= "";
        for (Object[] object: objList) {
            result += object[0] + " " + object[1] + "\n";
        }
        return result;
    }
}
