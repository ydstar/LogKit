package com.log.kit.print.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.log.kit.LogConfig;
import com.log.kit.print.LogPrinter;
import com.log.kit.util.MainHandler;


/**
 * Author: 信仰年轻
 * Date: 2020-09-08 17:27
 * Email: hydznsqk@163.com
 * Des: 将log显示在界面上
 * 思路:
 *      1.根据外界传进来的Activity对象找到根容器
 *      2.然后创建RecyclerView设置log数据
 *      3.把RecyclerView添加到跟容器中
 */
public class ViewPrinter implements LogPrinter {

    private RecyclerView mRecyclerView;
    private LogAdapter mAdapter;
    private final ViewPrintProvider mViewPrintProvider;

    public ViewPrinter(Activity activity){
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        mRecyclerView = new RecyclerView(activity);
        mAdapter = new LogAdapter(LayoutInflater.from(mRecyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mViewPrintProvider = new ViewPrintProvider(rootView, mRecyclerView,mAdapter);
    }


    @Override
    public void print(@NonNull LogConfig config, final int level, final String tag, @NonNull final String printString) {
        MainHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
                // 将log展示添加到recycleView
                mAdapter.addItem(new LogModel(System.currentTimeMillis(), level, tag, printString));
                // 滚动到对应的位置
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
    }


    public ViewPrintProvider getViewPrintProvider() {
        return mViewPrintProvider;
    }
}
