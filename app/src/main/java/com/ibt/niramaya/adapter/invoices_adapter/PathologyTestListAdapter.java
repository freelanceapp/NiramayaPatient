package com.ibt.niramaya.adapter.invoices_adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest;
import com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal.PharmacyBillMedicine;

import java.util.List;

public class PathologyTestListAdapter extends RecyclerView.Adapter<PathologyTestListAdapter.MyViewHolder> {

    private List<PathologyBillTest> billTests;
    private Context mContext;
    private View.OnClickListener onClickListener;

    public PathologyTestListAdapter(List<PathologyBillTest> billTests, Context mContext, View.OnClickListener onClickListener) {
        this.billTests = billTests;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pathology_test, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PathologyBillTest medicine = billTests.get(position);

        holder.tvTestName.setText(medicine.getTestName());
        holder.tvTestSample.setText(medicine.getTestSample());
        holder.tvTestCode.setText(medicine.getTestCode());
        holder.tvTestCost.setText(medicine.getTestCost());
        holder.tvTestSchedule.setText(medicine.getTestSchedule());
        holder.tvTestDiscount.setText(medicine.getTestDiscount());
        holder.tvTestType.setText(medicine.getTestType());
        holder.tvPrequestics.setText(medicine.getTestPrerequisites());



    /*
                    "pathology_bill_test_id": "1",
                    "test_name": "lab test1",
                    "test_id": "2",
                    "test_code": "abc",
                    "test_cost": "200",
                    "test_discount": "20",
                    "test_sample": "cbs",
                    "test_prerequisites": "none",
                    "test_type": "0",
                    "test_schedule": "morning",
                    "test_type_created_date": "2019-04-22 18:42:19"*/

    }

    @Override
    public int getItemCount() {
        return billTests.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTestName, tvTestSample, tvTestCode, tvTestCost, tvTestSchedule, tvTestDiscount, tvTestType, tvPrequestics;
        private CardView cardMedicineList;

        public MyViewHolder(View view) {
            super(view);
            tvTestName = view.findViewById(R.id.tvTestName);
            tvTestSample = view.findViewById(R.id.tvTestSample);
            tvTestCode = view.findViewById(R.id.tvTestCode);
            tvTestCost = view.findViewById(R.id.tvTestCost);
            tvTestSchedule = view.findViewById(R.id.tvTestSchedule);
            tvTestDiscount = view.findViewById(R.id.tvTestDiscount);
            tvTestType = view.findViewById(R.id.tvTestType);
            tvPrequestics = view.findViewById(R.id.tvPrequestics);
        }
    }

}
