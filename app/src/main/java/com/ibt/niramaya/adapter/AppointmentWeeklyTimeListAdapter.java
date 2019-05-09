package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.calander.DateOPD;
import com.ibt.niramaya.modal.calander.DayOPD;

import java.util.List;

public class AppointmentWeeklyTimeListAdapter extends RecyclerView.Adapter<AppointmentWeeklyTimeListAdapter.MyViewHolder> {

    private List<DayOPD> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public AppointmentWeeklyTimeListAdapter(List<DayOPD> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_appointement, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DayOPD dOPD = vendorLists.get(position);
        holder.tvAppointmentTime.setText(dOPD.getStartTime()+" - "+dOPD.getEndTime());
    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAppointmentTime;
        public MyViewHolder(View view) {
            super(view);
            tvAppointmentTime = view.findViewById(R.id.tvAppointmentTime);
        }
    }

}
