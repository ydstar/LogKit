package com.log.kit.print.view;



import com.log.kit.LogType;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 17:33
 * Email: hydznsqk@163.com
 * Des:
 */
public class LogModel {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMillis;
    public int level;
    public String tag;
    public String log;

    public LogModel(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    public String getFlattened() {
        return format(timeMillis) + " "+'/' + convertLevel(level) + '/' + tag + "/:";
    }

    private String convertLevel(int level){
        switch (level){
            case LogType.V:
              return "V";
            case LogType.D:
                return "D";
            case LogType.I:
                return "I";
            case LogType.W:
                return "W";
            case LogType.E:
                return "E";
            case LogType.A:
            default:
                return "A";
        }
    }

    private String format(long timeMillis) {
        return sdf.format(timeMillis);
    }
}
