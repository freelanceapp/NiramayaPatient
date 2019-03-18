package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;

import java.util.List;

public class SearchHospitalListAdapter extends RecyclerView.Adapter<SearchHospitalListAdapter.ViewHolder> {
    private View rootView;
    private Context mContext;
    private List<String> hospitaList;

    public SearchHospitalListAdapter(Context mContext, List<String> hospitaList) {
        this.mContext = mContext;
        this.hospitaList = hospitaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mContext);
        rootView = li.inflate(R.layout.row_hospital_list_search,null);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       /* Glide.with(mContext).load().into(viewHolder.imgHospital);
        viewHolder.tvHospitalName.setText();
        viewHolder.tvHospitalLocation.setText();
        viewHolder.tvHospitalRating.setText();*/

    }

    @Override
    public int getItemCount() {
        return hospitaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHospital;
        private TextView tvHospitalName,tvHospitalLocation,tvHospitalRating,tvDistance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHospital = itemView.findViewById(R.id.iv_hospital_image);
            tvHospitalName = itemView.findViewById(R.id.txtHospitalName);
            tvHospitalLocation = itemView.findViewById(R.id.tv_hospital_location);
            tvHospitalRating = itemView.findViewById(R.id.tv_hospital_rating);
        }
    }
}
