package com.ibt.niramaya.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.prescription.PTGivenModel;

import java.util.ArrayList;

public class PrescriptionGivenListAdapter extends RecyclerView.Adapter<PrescriptionGivenListAdapter.MyViewHolder> {

    private ArrayList<PTGivenModel> prescriptionList;
    private Context mContext;

    public PrescriptionGivenListAdapter(ArrayList<PTGivenModel> pastHistoryList, Context mContext) {
        this.prescriptionList = pastHistoryList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View itemView = li.inflate(R.layout.row_precription_list, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PTGivenModel prescriptionModel = prescriptionList.get(position);
        String type =prescriptionModel.getPrescriptionTreatmentType();
        switch (type) {
            case "Medicine":

                if (prescriptionModel.getPrescriptionContentType().equals("Image")){
                    holder.llMedicine.setVisibility(View.GONE);
                    holder.llTest.setVisibility(View.GONE);
                    holder.llCanvas.setVisibility(View.VISIBLE);
                    holder.llTestCanvas.setVisibility(View.GONE);

                    try {
                        holder.ivMedicine.setImageBitmap(getImageBitmap(prescriptionModel.getMedicineName()));
                        holder.ivDose.setImageBitmap(getImageBitmap(prescriptionModel.getMedicineDoes()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{

                    holder.llMedicine.setVisibility(View.VISIBLE);
                    holder.llTest.setVisibility(View.GONE);
                    holder.llCanvas.setVisibility(View.GONE);
                    holder.llTestCanvas.setVisibility(View.GONE);


                    holder.tvMedicine.setText(prescriptionModel.getMedicineName());
                    holder.tvDose.setText(prescriptionModel.getMedicineDoes());

                    try {
                        holder.ivMedicine.setImageBitmap(getImageBitmap(prescriptionModel.getMedicineName()));
                        holder.ivDose.setImageBitmap(getImageBitmap(prescriptionModel.getMedicineDoes()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case "Test":
                if (prescriptionModel.getPrescriptionContentType().equals("Image")) {
                    holder.llMedicine.setVisibility(View.GONE);
                    holder.llTest.setVisibility(View.GONE);
                    holder.llCanvas.setVisibility(View.GONE);
                    holder.llTestCanvas.setVisibility(View.VISIBLE);
                    holder.ivTest.setImageBitmap(getImageBitmap(prescriptionModel.getTestName()));
                } else {
                    holder.llMedicine.setVisibility(View.GONE);
                    holder.llTest.setVisibility(View.VISIBLE);
                    holder.llCanvas.setVisibility(View.GONE);
                    holder.llTestCanvas.setVisibility(View.GONE);
                    holder.tvTest.setText(prescriptionModel.getTestName());
                }

                break;
        }

    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout llMedicine, llCanvas, llTest, llTestCanvas;
        private ImageView ivMedicine, ivDose, ivTest;
        private TextView tvMedicine, tvDose, tvTest;

        public MyViewHolder(View view) {
            super(view);

            llCanvas = (LinearLayout)view.findViewById(R.id.llCanvas);
            ivMedicine = (ImageView) view.findViewById(R.id.ivMedicine);
            ivDose = (ImageView) view.findViewById(R.id.ivDose);

            llMedicine = (LinearLayout)view.findViewById(R.id.llMedicine);
            tvMedicine = (TextView)view.findViewById(R.id.tvMedicine);
            tvDose = (TextView)view.findViewById(R.id.tvDose);

            llTest = (LinearLayout)view.findViewById(R.id.llTest);
            tvTest = (TextView)view.findViewById(R.id.tvTest);

            llTestCanvas = (LinearLayout) view.findViewById(R.id.llTestCanvas);
            ivTest = (ImageView) view.findViewById(R.id.ivTest);

        }
    }

    private Bitmap getImageBitmap(String imgData){
        Bitmap bitmap = null;
        imgData = imgData.replace("data:image/png;base64,","");

        try {
            byte[] imageBytes = Base64.decode(imgData, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
            //ivHospitalLogo.setImageBitmap(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /***************************************************
    * String imgData = opdData.getHospialLogo();
        imgData = imgData.replace("data:image/png;base64,","");

        try {
            byte[] imageBytes = Base64.decode(imgData, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
            ivHospitalLogo.setImageBitmap(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    **/

}
