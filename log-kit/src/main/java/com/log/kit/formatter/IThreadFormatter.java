package com.log.kit.formatter;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 15:26
 * Email: hydznsqk@163.com
 * Des: 线程信息格式化
 */
public class IThreadFormatter implements ILogFormatter<Thread> {

    @Override
    public String format(Thread data) {
        return "Thread: " + data.getName();
    }

}
