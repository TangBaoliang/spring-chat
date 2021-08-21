package com.ctgu.j604.springbootchat.utils;

import java.util.Random;

public class NumUtils {
    public static String getNumStringByCounts(int n){
        String str = "";
        Random random = new Random();
        for(int i = 0; i<n; i++){

            str+=Math.abs(random.nextInt())%9+1;
        }
        return str;
    }
}
