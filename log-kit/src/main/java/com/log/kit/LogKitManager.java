package com.log.kit;

import androidx.annotation.NonNull;


import com.log.kit.print.LogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Author: 信仰年轻
 * Date: 2020-09-07 18:05
 * Email: hydznsqk@163.com
 * Des: LogKit的管理类
 */
public class LogKitManager {

    private LogConfig mConfig;
    private List<LogPrinter> mPrinters = new ArrayList<>();

    private LogKitManager() {
    }

    public static class SingleHolder {
        public static volatile LogKitManager instance = new LogKitManager();
    }

    public static LogKitManager getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 初始化
     * @param config
     * @param printers
     */
    public void init(@NonNull LogConfig config, LogPrinter... printers) {
        this.mConfig = config;
        this.mPrinters.addAll(Arrays.asList(printers));
    }

    public LogConfig getConfig() {
        check();
        return mConfig;
    }

    public List<LogPrinter> getPrinters() {
        check();
        return mPrinters;
    }

    public void addPrinter(LogPrinter printer) {
        check();
        mPrinters.add(printer);
    }

    public void removePrinter(LogPrinter printer) {
        check();
        if (mPrinters != null) {
            mPrinters.remove(printer);
        }
    }

    private void check() {
        if (mConfig == null || mPrinters == null) {
            throw new RuntimeException("LogKitManager 的 init 还未初始化");
        }
    }

}
