package com.log.kit.print.console;

import android.util.Log;
import androidx.annotation.NonNull;
import com.log.kit.LogConfig;
import com.log.kit.print.LogPrinter;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 15:29
 * Email: hydznsqk@163.com
 * Des:LogKit的控制台打印器
 */
public class ConsolePrinter implements LogPrinter {

    @Override
    public void print(@NonNull LogConfig config, int level, String tag, @NonNull String printString) {

        int length = printString.length();
        //一共有countOfSub行
        int countOfSub = length/ LogConfig.MAX_LEN;

        if (countOfSub > 0) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int x = 0; x < countOfSub; x++) {
                //裁剪字符串
                String substring = printString.substring(index, index + LogConfig.MAX_LEN);
                sb.append(substring);
                index += LogConfig.MAX_LEN;
            }
            if(index!=length){
                sb.append(printString.substring(index,length));
            }
            //调用官方的Log打印拼接好的字符串
            Log.println(level, tag, sb.toString());
        }else{
            Log.println(level, tag, printString);
        }
    }
}
