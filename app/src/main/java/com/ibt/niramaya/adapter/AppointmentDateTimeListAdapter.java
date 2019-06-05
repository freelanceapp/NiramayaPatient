package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.interfaces.InitScheduleList;
import com.ibt.niramaya.modal.calander.DateOPD;
import com.ibt.niramaya.modal.calander.DayOPD;

import java.util.List;

public class AppointmentDateTimeListAdapter extends RecyclerView.Adapter<AppointmentDateTimeListAdapter.MyViewHolder> {

    private List<DateOPD> vendorLists;
    private Context mContext;
    private InitScheduleList listener;
    private int row_index = -1;

    public AppointmentDateTimeListAdapter(List<DateOPD> vendorLists, Context mContext, InitScheduleList onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.listener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_appointement, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DateOPD dOPD = vendorLists.get(position);
        holder.tvAppointmentTime.setText(dOPD.getStartTime()+" - "+dOPD.getEndTime());
        holder.rlSchedule.setOnClickListener(v -> {
            row_index = position;
            notifyDataSetChanged();
            listener.initScheduleList(position, "Date", null, dOPD);
        });

        if (row_index == position) {
            holder.rlSchedule.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_rhl7));
        } else {
            holder.rlSchedule.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_rhl8));
        }
    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAppointmentTime;
        RelativeLayout rlSchedule;
        public MyViewHolder(View view) {
            super(view);
            tvAppointmentTime = view.findViewById(R.id.tvAppointmentTime);
            rlSchedule = view.findViewById(R.id.rlSchedule);
        }
    }

}
