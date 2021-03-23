package com.example.logkit;

import android.app.Application;

import com.google.gson.Gson;
import com.log.kit.ILogConfig;
import com.log.kit.ILogManager;
import com.log.kit.print.ILogPrinter;
import com.log.kit.print.console.IConsolePrinter;


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

        ILogConfig iLogConfig = new ILogConfig() {
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
            public ILogPrinter[] printers() {
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

        ILogManager.getInstance().init(iLogConfig, new IConsolePrinter());


    }
}
