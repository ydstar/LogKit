package com.example.logkit;

import android.app.Application;

import com.google.gson.Gson;
import com.log.kit.LogConfig;
import com.log.kit.LogKitManager;
import com.log.kit.print.LogPrinter;
import com.log.kit.print.console.ConsolePrinter;


/**
 * Author: 信仰年轻
 * Date: 2020-09-08 16:38
 * Email: hydznsqk@163.com
 * Des:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LogConfig iLogConfig = new LogConfig() {
            @Override
            public String getGlobalTag() {
                return super.getGlobalTag();
            }

            @Override
            public boolean enable() {
                return super.enable();
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 0;
            }

            @Override
            public LogPrinter[] printers() {
                return super.printers();
            }

            @Override
            public JsonParser injectJsonParser() {
                JsonParser parser = new JsonParser() {
                    @Override
                    public String toJson(Object object) {
                        String json = new Gson().toJson(object);
                        return json;
                    }
                };
                return parser;
            }
        };

        LogKitManager.getInstance().init(iLogConfig, new ConsolePrinter());


    }
}
