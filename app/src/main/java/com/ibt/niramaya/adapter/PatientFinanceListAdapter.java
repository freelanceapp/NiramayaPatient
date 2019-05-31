package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.finance.PatientFinance;

import java.util.List;

public class PatientFinanceListAdapter extends RecyclerView.Adapter<PatientFinanceListAdapter.MyViewHolder> {

    private List<PatientFinance> patientFinance;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PatientFinanceListAdapter(List<PatientFinance> opdInformationList, Context mContext, View.OnClickListener onClickListener) {
        this.patientFinance = opdInformationList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient_finance_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PatientFinance datum = patientFinance.get(position);

        holder.tvPolicyProviderName.setText(datum.getFinanceProvider());
        holder.tvPolicyName.setText("Policy Name : "+datum.getFinanceTitle());
        holder.tvPolicyNumber.setText("Policy Number : "+datum.getFinancePolicyNumber());

        holder.imgViewDoc.setTag(position);
        holder.imgViewDoc.setOnClickListener(onClickListener);

        holder.llRoot.setTag(position);
        holder.llRoot.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return patientFinance.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgViewDoc;
        private LinearLayout llRoot;
        private TextView tvPolicyProviderName, tvPolicyName, tvPolicyNumber;
        public MyViewHolder(View view) {
            super(view);
            imgViewDoc = view.findViewById(R.id.imgViewDoc);
            llRoot = view.findViewById(R.id.llRoot);
            tvPolicyProviderName = view.findViewById(R.id.tvPolicyProviderName);
            tvPolicyName = view.findViewById(R.id.tvPolicyName);
            tvPolicyNumber = view.findViewById(R.id.tvPolicyNumber);
        }
    }

}
