package com.example.logkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.log.kit.LogKit;
import com.log.kit.LogKitManager;
import com.log.kit.print.view.ViewPrintProvider;
import com.log.kit.print.view.ViewPrinter;


public class LogKitDemoActivity extends AppCompatActivity {
    private ViewPrinter mViewPrinter;
    private ViewPrintProvider mPrintProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_kit_demo);
        mViewPrinter = new ViewPrinter(this);
        LogKitManager.getInstance().addPrinter(mViewPrinter);

        mPrintProvider = mViewPrinter.getViewPrintProvider();
        mPrintProvider.showFloatingView();
    }

    public void onClickPrint(View view){
        LogKit.v("令狐冲");
        LogKit.d("令狐冲");
        LogKit.i("令狐冲");
        LogKit.w("令狐冲");
        LogKit.e("令狐冲");
        LogKit.a("令狐冲");

    }
    public void onClickViewPrint(View view){
        LogKit.vt("tag", "风清扬");
        LogKit.dt("tag", "风清扬");
        LogKit.it("tag", "风清扬");
        LogKit.wt("tag", "风清扬");
        LogKit.et("tag", "风清扬");
        LogKit.at("tag", "风清扬");
    }

    public void open(View view){
        mPrintProvider.showFloatingView();
    }

    public void close(View view){
        mPrintProvider.closeFloatingView();
    }

    @Override
    protected void onDestroy() {
        LogKitManager.getInstance().removePrinter(mViewPrinter);
        super.onDestroy();

    }
}