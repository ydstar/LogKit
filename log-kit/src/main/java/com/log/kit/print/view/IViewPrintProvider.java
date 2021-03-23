package com.log.kit.print.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.log.kit.util.IDisplayUtil;


/**
 * Author: 信仰年轻
 * Date: 2020-09-08 17:39
 * Email: hydznsqk@163.com
 * Des:
 */
public class IViewPrintProvider {

    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    private FrameLayout mRootView;
    private View mFloatingView;
    private boolean mIsOpen;
    private FrameLayout mLogView;
    private RecyclerView mRecyclerView;
    private ILogAdapter mAdapter;

    public IViewPrintProvider(FrameLayout rootView, RecyclerView recyclerView, ILogAdapter adapter) {
        this.mRootView = rootView;
        this.mRecyclerView = recyclerView;
        this.mAdapter = adapter;
    }

    /**
     * 显示Log悬浮按钮
     */
    public void showFloatingView() {
        if (mRootView.findViewWithTag(TAG_FLOATING_VIEW) != null) {
            return;
        }

        int index = mRootView.indexOfChild(getFloatingView());
        View childAt = mRootView.getChildAt(index);
        if(childAt==null){
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM | Gravity.END;
            View floatingView = getFloatingView();
            floatingView.setTag(TAG_FLOATING_VIEW);
            floatingView.setBackgroundColor(Color.BLACK);
            floatingView.setAlpha(0.8f);
            params.bottomMargin = IDisplayUtil.dp2px(100, mRootView.getResources());

            mRootView.addView(getFloatingView(), params);
        }
    }

    /**
     * 关闭Log 悬浮按钮
     */
    public void closeFloatingView() {
        mRootView.removeView(getFloatingView());
    }

    private View getFloatingView() {
        if (mFloatingView != null) {
            return mFloatingView;
        }
        TextView textView = new TextView(mRootView.getContext());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsOpen) {
                    showLogView();
                }
            }
        });
        textView.setText("iLog");
        return mFloatingView = textView;
    }

    /**
     * 展示内容的LogView
     */
    private void showLogView() {
        if (mRootView.findViewWithTag(TAG_LOG_VIEW) != null) {
            return;
        }
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, IDisplayUtil.dp2px(260, mRootView.getResources()));
        params.gravity = Gravity.BOTTOM;
        View logView = getLogView();
        logView.setTag(TAG_LOG_VIEW);
        mRootView.addView(getLogView(), params);

        mIsOpen = true;
    }

    /**
     * 关闭内容的LogView
     */
    private void closeLogView() {
        mIsOpen = false;
        mRootView.removeView(getLogView());
    }

    private View getLogView() {
        if (mLogView != null) {
            return mLogView;
        }
        FrameLayout logView = new FrameLayout(mRootView.getContext());
        logView.setBackgroundColor(Color.BLACK);
        logView.addView(mRecyclerView);

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.END;
        TextView closeView = new TextView(mRootView.getContext());
        closeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeLogView();
            }
        });
        closeView.setText("Close");
        logView.addView(closeView, params);


        FrameLayout.LayoutParams params2 =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.END;
        params2.topMargin = IDisplayUtil.dp2px(30, mRootView.getResources());
        TextView clearView = new TextView(mRootView.getContext());
        clearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
            }
        });
        clearView.setText("Clear");
        logView.addView(clearView, params2);

        return this.mLogView = logView;
    }


}
