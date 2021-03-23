package com.log.kit.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


public class MainHandler {

    private MainHandler() {
    }

    private static class SingleHolder {
        public static volatile MainHandler INSTANCE = new MainHandler();
    }

    public static MainHandler getInstance() {
        return SingleHolder.INSTANCE;
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public void post(Runnable runnable){
        mHandler.post(runnable);
    }
    public void postDelay(Long delayMills,Runnable runnable){
        mHandler.postDelayed(runnable,delayMills);
    }

    public void sendAtFrontOfQueue(Runnable runnable){
        Message message = Message.obtain(mHandler, runnable);
        mHandler.sendMessageAtFrontOfQueue(message);
    }

    public void remove(Runnable runnable){
        mHandler.removeCallbacks(runnable);
    }
}
