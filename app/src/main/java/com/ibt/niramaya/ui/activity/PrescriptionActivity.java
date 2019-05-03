package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.DoctorReviewRatingAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.prescription.OpdList;
import com.ibt.niramaya.modal.prescription.detail.Preception;
import com.ibt.niramaya.modal.prescription.detail.PrescriptionDetailModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

public class PrescriptionActivity extends BaseActivity implements View.OnClickListener {

    private OpdList opdData;
    private TextView tvPatientName, tvPatientId, tvDoctorName, tvHospitalName, tvOpdCreatedDate, tvAge, tvContact,
            tvComplaint, tvBpCount, tvHrCount, tvRespCount, tvTempCount, tvPainScoreCount, tvDischargeType;
    private ImageView ivHospitalLogo, ivBack;
    private Preception preceptionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        initViews();
    }

    private void initViews() {
        opdData = (OpdList) getIntent().getParcelableExtra("OPD_DATA");

        tvPatientName = findViewById(R.id.tvPatientName);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvHospitalName = findViewById(R.id.tvHospitalName);
        tvOpdCreatedDate = findViewById(R.id.tvOpdCreatedDate);
        tvAge = findViewById(R.id.tvAge);
        tvContact = findViewById(R.id.tvContact);
        ivHospitalLogo = findViewById(R.id.ivHospitalLogo);
        ivBack = findViewById(R.id.ivBack);

        tvComplaint = findViewById(R.id.tvComplaint);
        tvBpCount = findViewById(R.id.tvBpCount);
        tvHrCount = findViewById(R.id.tvHrCount);
        tvRespCount = findViewById(R.id.tvRespCount);
        tvTempCount = findViewById(R.id.tvTempCount);
        tvPainScoreCount = findViewById(R.id.tvPainScoreCount);
        tvDischargeType = findViewById(R.id.tvDischargeType);

        tvPatientName.setText(AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_NAME));
        tvPatientId.setText("Patient Id : "+AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID));
        tvDoctorName.setText(opdData.getDoctorName());
        tvHospitalName.setText(opdData.getHospitalName());
        tvOpdCreatedDate.setText(changeDateFormat(opdData.getHospitalCreatedDate()));

        ivBack.setOnClickListener(this);

        String imgData = opdData.getHospialLogo();
        imgData = imgData.replace("data:image/png;base64,","");

        try {
            byte[] imageBytes = Base64.decode(imgData, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
            ivHospitalLogo.setImageBitmap(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fetchPrescriptionDetail();

    }

    private void fetchPrescriptionDetail() {

        String uId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        String pId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);
        String opdId = opdData.getOpdId();

        if (cd.isNetworkAvailable()){
            RetrofitService.patientPrescriptionDetail(new Dialog(mContext), retrofitApiClient.patientPrescriptionDetail(
                    uId, pId, opdId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PrescriptionDetailModel detailModel = (PrescriptionDetailModel) result.body();
                    if (!detailModel.getError()){
                        preceptionData = detailModel.getPreception();
                        //tvComplaint, tvBpCount, tvHrCount, tvRespCount, tvTempCount, tvPainScoreCount
                        tvComplaint.setText(preceptionData.getOpdCheifComplaints());
                        tvBpCount.setText(preceptionData.getOpdBp());
                        tvHrCount.setText(preceptionData.getOpdHeartRatePerMin());
                        tvRespCount.setText(preceptionData.getOpdRespRateMin());
                        tvTempCount.setText(preceptionData.getOpdTemp());
                        tvPainScoreCount.setText(preceptionData.getOpdPainScore());
                        tvDischargeType.setText(preceptionData.getOpdTypeOfDischarge());
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    public String changeDateFormat(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                onBackPressed();
                break;
        }
    }
}
