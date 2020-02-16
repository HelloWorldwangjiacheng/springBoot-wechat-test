package com.imooc.sell.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey(){
        //为防止在多线程情况下导致相同，我们加上synchronized关键字
        Random random = new Random();
        //保持随机数的位数相同,都是6位
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
}
