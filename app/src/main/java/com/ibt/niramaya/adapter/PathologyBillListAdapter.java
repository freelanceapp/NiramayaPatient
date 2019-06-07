package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.prescription.detail.BillMedicine;
import com.ibt.niramaya.modal.prescription.detail.BillTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PathologyBillListAdapter extends RecyclerView.Adapter<PathologyBillListAdapter.MyViewHolder> {

    private ArrayList<BillTest> medicineList;
    private Context mContext;
    private View.OnClickListener clickListener;

    public PathologyBillListAdapter(ArrayList<BillTest> pastHistoryList, Context mContext, View.OnClickListener clickListener) {
        this.medicineList = pastHistoryList;
        this.mContext = mContext;
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_pathology_bill_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BillTest medicineBill = medicineList.get(position);
        holder.tvBillTitle.setText(medicineBill.getPathologyTestName());
        holder.tvBillDate.setText(changeDateFormat(medicineBill.getPathologyBillTestTypeCreatedDate()));
        holder.llRoot.setTag(position);
        holder.llRoot.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llRoot;
        private TextView tvBillTitle, tvBillDate;

        public MyViewHolder(View view) {
            super(view);

            llRoot = view.findViewById(R.id.llPathologyRoot);

            tvBillTitle = view.findViewById(R.id.tvBillTitle);
            tvBillDate = view.findViewById(R.id.tvBillDate);

        }
    }

    public String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);

        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
