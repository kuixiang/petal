package com.sunflower.petal.utils;

import java.io.File;

/**
 * Created by xiangkui on 14-2-23.
 */
public class FileUtil {
    public static String getExtName(File file){
        String name=file.getName();
        return getExtName(name);
    }
    /*
 * Java文件操作 获取文件扩展名
 *
 *  Created on: 2011-8-2
 *      Author: blueeagle
 */
    public static String getExtName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
