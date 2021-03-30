package com.log.kit.print;

import androidx.annotation.NonNull;

import com.log.kit.LogConfig;


/**
 * Author: 信仰年轻
 * Date: 2020-09-08 15:25
 * Email: hydznsqk@163.com
 * Des:LogPrinter的日志打印接口,基于该接口可以自定义日志打印方式
 */
public interface LogPrinter {

    void print(@NonNull LogConfig config, int level, String tag, @NonNull String printString);
}
