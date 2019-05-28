package com.ibt.niramaya.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.ui.activity.invoice_data.BookAppointmentActivityKt;

import java.util.List;

public class HospitalCategoryAdapter extends RecyclerView.Adapter<HospitalCategoryAdapter.MyViewHolder> {

    private List<String> vendorLists;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public HospitalCategoryAdapter(List<String> vendorLists, Context mContext, View.OnClickListener onClickListener) {
        this.vendorLists = vendorLists;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*holder.restaurent_name.setText(vendorLists.get(position).getVendorName());
        holder.restaurent_address.setText(vendorLists.get(position).getVendorStreet());
        holder.cardViewItem.setTag(position);
        holder.cardViewItem.setOnClickListener(onClickListener);

        String sImg = Constant.BASE_URL + vendorLists.get(position).getVendorLogo();
        Glide.with(mContext).load(sImg)
                .into(holder.rc_img);*/
        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BookAppointmentActivityKt.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vendorLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurent_name, txtCategoryName;
        public ImageView imgCategory;
        private CardView cardViewItem;

        public MyViewHolder(View view) {
            super(view);
            txtCategoryName = view.findViewById(R.id.txtCategoryName);
            /*cardViewItem = view.findViewById(R.id.cardViewItem);
            restaurent_address = view.findViewById(R.id.restaurent_address);
            restaurent_name = view.findViewById(R.id.restaurent_name);
            rc_img = view.findViewById(R.id.restaurent_img);*/
            imgCategory = view.findViewById(R.id.imgCategory);
        }
    }

}
