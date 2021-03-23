package com.log.kit.util;

import android.app.Application;

public class AppGlobal {


    private static Application mApplication = null;

    public static Application getInstance() {
        if (mApplication == null) {
            try {
                mApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mApplication;
    }

}
