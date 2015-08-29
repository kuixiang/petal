package com.sunflower.petal.utils;

/**
 * Created by xiangkui on 2015/2/1.
 */
public class CommonUtil {
    public static boolean IsNull(Object obj) { return !IsNotNull(obj);}
    public static boolean IsNotNull(Object obj){
        return obj != null;
    }
}
