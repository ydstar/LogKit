package com.log.kit.print.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.log.kit.R;
import com.log.kit.LogType;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 信仰年轻
 * Date: 2020-09-08 17:34
 * Email: hydznsqk@163.com
 * Des:
 */
public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {


    private LayoutInflater inflater;

    private List<LogModel> mLogs = new ArrayList<>();

    public LogAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.log_kit_item, parent, false);
        return new LogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        LogModel logItem = mLogs.get(position);

        int color = getHighlightColor(logItem.level);
        holder.tagView.setTextColor(color);
        holder.messageView.setTextColor(color);

        holder.tagView.setText(logItem.getFlattened());
        holder.messageView.setText(logItem.log);
    }

    /**
     * 跟进log级别获取不同的高了颜色
     *
     * @param logLevel log 级别
     * @return 高亮的颜色
     */
    private int getHighlightColor(int logLevel) {
        int highlight;
        switch (logLevel) {
            case LogType.V:
                highlight = Color.parseColor("#BBBBBB");
                break;
            case LogType.D:
                highlight = Color.parseColor("#0070BB");
                break;
            case LogType.I:
                highlight = Color.parseColor("#48BB31");
                break;
            case LogType.W:
                highlight = Color.parseColor("#BBBB23");
                break;
            case LogType.E:
                highlight = Color.parseColor("#FF0006");
                break;
            case LogType.A:
            default:
                highlight = Color.parseColor("#8F0005");
                break;
        }
        return highlight;
    }

    @Override
    public int getItemCount() {
        return mLogs.size();
    }


    public void addItem(LogModel logItem) {
        mLogs.add(logItem);
        notifyItemInserted(mLogs.size() - 1);
    }

    public void clear(){
        mLogs.clear();
        notifyDataSetChanged();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;

        LogViewHolder(View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
