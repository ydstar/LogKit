package com.log.kit;

import androidx.annotation.NonNull;


import com.log.kit.print.ILogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Author: 信仰年轻
 * Date: 2020-09-07 18:05
 * Email: hydznsqk@163.com
 * Des: iLog的管理类
 */
public class ILogManager {

    private ILogConfig mConfig;
    private List<ILogPrinter> mPrinters = new ArrayList<>();

    private ILogManager() {
    }

    public static class SingleHolder {
        public static volatile ILogManager instance = new ILogManager();
    }

    public static ILogManager getInstance() {
        return SingleHolder.instance;
    }

    /**
     * 初始化
     * @param config
     * @param printers
     */
    public void init(@NonNull ILogConfig config, ILogPrinter... printers) {
        this.mConfig = config;
        this.mPrinters.addAll(Arrays.asList(printers));
    }

    public ILogConfig getConfig() {
        check();
        return mConfig;
    }

    public List<ILogPrinter> getPrinters() {
        check();
        return mPrinters;
    }

    public void addPrinter(ILogPrinter printer) {
        check();
        mPrinters.add(printer);
    }

    public void removePrinter(ILogPrinter printer) {
        check();
        if (mPrinters != null) {
            mPrinters.remove(printer);
        }
    }

    private void check() {
        if (mConfig == null || mPrinters == null) {
            throw new RuntimeException("ILogManager 的 init 还未初始化");
        }
    }

}
