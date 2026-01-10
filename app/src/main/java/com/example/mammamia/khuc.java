package com.example.mammamia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder> {

    private Context context;
    private List<AlertRecord> alertList;

    public AlertAdapter(Context context, List<AlertRecord> alertList) {
        this.context = context;
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlertRecord record = alertList.get(position);
        holder.tvTime.setText(record.getTime());
        holder.tvMessage.setText(record.getMessage());

        // 리스트 아이템 클릭 시 지도 뷰어(MapViewerActivity)로 이동
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MapViewerActivity.class);
            intent.putExtra("lat", record.getLatitude());
            intent.putExtra("lng", record.getLongitude());
            intent.putExtra("time", record.getTime());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvMessage = itemView.findViewById(R.id.tv_message);
        }
    }
}