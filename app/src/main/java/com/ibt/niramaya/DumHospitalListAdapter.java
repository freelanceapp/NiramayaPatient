package com.ibt.niramaya;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.modal.hospital.HospitalDatum;
import com.ibt.niramaya.ui.activity.HospitalDetailActivity;

import java.util.List;

public class DumHospitalListAdapter extends RecyclerView.Adapter<DumHospitalListAdapter.MyViewHolder> {

    private List<String> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public DumHospitalListAdapter(List<String> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hospital_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        /*holder.restaurent_name.setText(vendorLists.get(position).getVendorName());
        holder.restaurent_address.setText(vendorLists.get(position).getVendorStreet());
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        String sImg = Constant.BASE_URL + vendorLists.get(position).getVendorLogo();
        Glide.with(mContext).load(sImg)
                .into(holder.rc_img);*/
        holder.txtOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HospitalDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurent_name, txtOpen;
        public ImageView rc_img;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            cardViewItem = view.findViewById(R.id.cardViewItem);
            txtOpen = view.findViewById(R.id.txtOpen);
            restaurent_name = view.findViewById(R.id.restaurent_name);
            rc_img = view.findViewById(R.id.restaurent_img);
        }
    }

}
