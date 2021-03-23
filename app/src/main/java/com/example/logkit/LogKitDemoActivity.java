package com.example.logkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.log.kit.ILog;
import com.log.kit.ILogManager;
import com.log.kit.print.view.IViewPrintProvider;
import com.log.kit.print.view.IViewPrinter;

public class LogKitDemoActivity extends AppCompatActivity {
    private IViewPrinter mViewPrinter;
    private IViewPrintProvider mPrintProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_kit_demo);
        mViewPrinter = new IViewPrinter(this);
        ILogManager.getInstance().addPrinter(mViewPrinter);

        mPrintProvider = mViewPrinter.getViewPrintProvider();
        mPrintProvider.showFloatingView();
    }

    public void onClickPrint(View view){
        ILog.v("令狐冲");
        ILog.d("令狐冲");
        ILog.i("令狐冲");
        ILog.w("令狐冲");
        ILog.e("令狐冲");
        ILog.a("令狐冲");

    }
    public void onClickViewPrint(View view){
        ILog.vt("tag", "风清扬");
        ILog.dt("tag", "风清扬");
        ILog.it("tag", "风清扬");
        ILog.wt("tag", "风清扬");
        ILog.et("tag", "风清扬");
        ILog.at("tag", "风清扬");
    }

    public void open(View view){
        mPrintProvider.showFloatingView();
    }

    public void close(View view){
        mPrintProvider.closeFloatingView();
    }

    @Override
    protected void onDestroy() {
        ILogManager.getInstance().removePrinter(mViewPrinter);
        super.onDestroy();

    }
}