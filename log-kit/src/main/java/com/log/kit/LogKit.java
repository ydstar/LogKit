package com.log.kit;



import com.log.kit.util.StackTraceUtil;
import com.log.kit.print.LogPrinter;

import java.util.Arrays;
import java.util.List;


/**
 * Author: 信仰年轻
 * Date: 2020-09-07 17:57
 * Email: hydznsqk@163.com
 * Des: LogKit的对外使用类
 * 打印堆栈信息
 * File输出
 * 模拟控制台
 */
public class LogKit {

    private static final String I_LOG_PACKAGE;

    static {
        String className = LogKit.class.getName();
        I_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(LogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(LogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(LogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(LogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(LogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(LogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(LogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(LogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(LogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(LogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(LogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(LogType.A, tag, contents);
    }

    /**
     * log打印
     *
     * @param type
     * @param contents
     */
    private static void log(@LogType.TYPE int type, Object... contents) {
        String globalTag = LogKitManager.getInstance().getConfig().getGlobalTag();
        log(type, globalTag, contents);
    }

    /**
     * log打印
     *
     * @param type
     * @param tag
     * @param contents
     */
    private static void log(@LogType.TYPE int type, String tag, Object... contents) {
        LogConfig config = LogKitManager.getInstance().getConfig();
        log(config, type, tag, contents);
    }

    /**
     * log打印
     *
     * @param config
     * @param type
     * @param tag
     * @param contents
     */
    private static void log(LogConfig config, @LogType.TYPE int type, String tag, Object... contents) {
        //如果iLog是关闭的,直接return
        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        //拼接线程信息
        if (config.includeThread()) {
            //对当前的线程进行信息的格式化
            String threadInfo = LogConfig.I_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        //拼接堆栈信息
        if (config.stackTraceDepth() > 0) {
            //获取堆栈信息
            StackTraceElement[] traceElements = StackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), I_LOG_PACKAGE, config.stackTraceDepth());
            //格式化堆栈信息
            String traceInfo = LogConfig.I_STACK_TRACE_FORMATTER.format(traceElements);
            sb.append(traceInfo).append("\n");
        }


        //拼接contents信息
        String body =  parseBody(contents,config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);

        //拿到初始时候自己定义的打印器,然后进行打印,看是打印到文件,还是控制台,还是文件中
        List<LogPrinter> printerList = config.printers() !=null ? Arrays.asList(config.printers()) : LogKitManager.getInstance().getPrinters();
        if (printerList == null) {
            return;
        }
        //打印log
        for(LogPrinter printer:printerList){
            printer.print(config,type,tag,sb.toString());
        }

    }

    /**
     * 解析body
     * @param contents
     * @param config
     * @return
     */
    private static String parseBody(Object[] contents, LogConfig config) {
        //如果传进来的对象解析json不为空
        if(config.injectJsonParser()!=null){
            if(contents.length==1 && contents[0] instanceof String){
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();

        for(Object o:contents){
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

}
