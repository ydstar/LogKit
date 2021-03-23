package com.log.kit;



import com.log.kit.common.ILogType;
import com.log.kit.common.IStackTraceUtil;
import com.log.kit.print.ILogPrinter;

import java.util.Arrays;
import java.util.List;


/**
 * Author: 信仰年轻
 * Date: 2020-09-07 17:57
 * Email: hydznsqk@163.com
 * Des: iLog的对外使用类
 * 打印堆栈信息
 * File输出
 * 模拟控制台
 */
public class ILog {

    private static final String I_LOG_PACKAGE;

    static {
        String className = ILog.class.getName();
        I_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(ILogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(ILogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(ILogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(ILogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(ILogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(ILogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(ILogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(ILogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(ILogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(ILogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(ILogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(ILogType.A, tag, contents);
    }

    /**
     * log打印
     *
     * @param type
     * @param contents
     */
    private static void log(@ILogType.TYPE int type, Object... contents) {
        String globalTag = ILogManager.getInstance().getConfig().getGlobalTag();
        log(type, globalTag, contents);
    }

    /**
     * log打印
     *
     * @param type
     * @param tag
     * @param contents
     */
    private static void log(@ILogType.TYPE int type, String tag, Object... contents) {
        ILogConfig config = ILogManager.getInstance().getConfig();
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
    private static void log(ILogConfig config, @ILogType.TYPE int type, String tag, Object... contents) {
        //如果iLog是关闭的,直接return
        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        //拼接线程信息
        if (config.includeThread()) {
            //对当前的线程进行信息的格式化
            String threadInfo = ILogConfig.I_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        //拼接堆栈信息
        if (config.stackTraceDepth() > 0) {
            //获取堆栈信息
            StackTraceElement[] traceElements = IStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), I_LOG_PACKAGE, config.stackTraceDepth());
            //格式化堆栈信息
            String traceInfo = ILogConfig.I_STACK_TRACE_FORMATTER.format(traceElements);
            sb.append(traceInfo).append("\n");
        }


        //拼接contents信息
        String body =  parseBody(contents,config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);

        //拿到初始时候自己定义的打印器,然后进行打印,看是打印到文件,还是控制台,还是文件中
        List<ILogPrinter> printerList = config.printers() !=null ? Arrays.asList(config.printers()) : ILogManager.getInstance().getPrinters();
        if (printerList == null) {
            return;
        }
        //打印log
        for(ILogPrinter printer:printerList){
            printer.print(config,type,tag,sb.toString());
        }

    }

    /**
     * 解析body
     * @param contents
     * @param config
     * @return
     */
    private static String parseBody(Object[] contents, ILogConfig config) {
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
