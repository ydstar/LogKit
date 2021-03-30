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

        LogConfig logConfig = new LogConfig() {
            @Override
            public String getGlobalTag() {
                //全局的tag
                return super.getGlobalTag();
            }

            @Override
            public boolean enable() {
                //LogKit是否可用
                return super.enable();
            }

            @Override
            public boolean includeThread() {
                //是否包含线程信息
                return true;
            }

            @Override
            public int stackTraceDepth() {
                //堆栈的深度
                return 0;
            }

            @Override
            public LogPrinter[] printers() {
                //打印器
                return super.printers();
            }

            @Override
            public JsonParser injectJsonParser() {
                //外界注入对象的序列化
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
        //初始化配置并添加控制台打印器,支持添加多个打印器
        LogKitManager.getInstance().init(logConfig, new ConsolePrinter());

    }
}
