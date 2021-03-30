package com.log.kit.formatter;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 15:24
 * Email: hydznsqk@163.com
 * Des: LogKit的格式化接口
 */
public interface LogFormatter<T> {

    String format(T data);
}
