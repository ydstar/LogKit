package com.log.kit;


import com.log.kit.formatter.IStackTraceFormatter;
import com.log.kit.formatter.IThreadFormatter;
import com.log.kit.print.ILogPrinter;

/**
 * Author: 信仰年轻
 * Date: 2020-09-07 18:05
 * Email: hydznsqk@163.com
 * Des: iLog的配置
 */
public abstract class ILogConfig {


    //每行的最大长度为512
    public static int MAX_LEN = 512;

    //堆栈的深度
    private int STACK_TRACE_DEPTH = 5;

    //线程信息格式化对象
    public static IThreadFormatter I_THREAD_FORMATTER = new IThreadFormatter();

    //堆栈信息格式化对象
    public static IStackTraceFormatter I_STACK_TRACE_FORMATTER = new IStackTraceFormatter();

    /**
     * 全局的tag
     *
     * @return
     */
    public String getGlobalTag() {
        return "iLog";
    }

    /**
     * iLog是否可用
     *
     * @return
     */
    public boolean enable() {
        return true;
    }

    /**
     * 是否包含线程信息
     *
     * @return
     */
    public boolean includeThread() {
        return true;
    }

    /**
     * 堆栈的深度
     *
     * @return
     */
    public int stackTraceDepth() {
        return STACK_TRACE_DEPTH;
    }

    /**
     * 打印器
     *
     * @return
     */
    public ILogPrinter[] printers() {
        return null;
    }


    //外界注入对象的序列化
    public JsonParser injectJsonParser() {
        return null;
    }

    //接口
    public interface JsonParser {
        String toJson(Object object);
    }


}
