package com.aochong.for52pojie.util;

import java.util.List;
import java.util.Random;

/**
 * list工具类
 *
 * @author zhangaochong
 * @date 2019-03-02 13:30
 */
public class ListUtils {
    /**
     * 随机获取list内容
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T randomGet(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
