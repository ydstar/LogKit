package com.log.kit.print.view;



import com.log.kit.common.ILogType;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 17:33
 * Email: hydznsqk@163.com
 * Des:
 */
public class ILogModel {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
    public long timeMillis;
    public int level;
    public String tag;
    public String log;

    public ILogModel(long timeMillis, int level, String tag, String log) {
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
            case ILogType.V:
              return "V";
            case ILogType.D:
                return "D";
            case ILogType.I:
                return "I";
            case ILogType.W:
                return "W";
            case ILogType.E:
                return "E";
            case ILogType.A:
            default:
                return "A";
        }
    }

    private String format(long timeMillis) {
        return sdf.format(timeMillis);
    }
}
