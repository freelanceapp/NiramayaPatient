package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.PrescriptionAdvisedListAdapter;
import com.ibt.niramaya.adapter.PrescriptionGivenListAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.prescription.OpdList;
import com.ibt.niramaya.modal.prescription.PTAdvisedModel;
import com.ibt.niramaya.modal.prescription.PTGivenModel;
import com.ibt.niramaya.modal.prescription.detail.Medicine;
import com.ibt.niramaya.modal.prescription.detail.Preception;
import com.ibt.niramaya.modal.prescription.detail.PrescriptionDetailModel;
import com.ibt.niramaya.modal.prescription.detail.Test;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Response;

public class PrescriptionActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private RelativeLayout leftDrawer;
    private RelativeLayout rightDrawer;

    private OpdList opdData;
    private TextView tvPatientName, tvPatientId, tvDoctorName, tvHospitalName, tvOpdCreatedDate, tvAge, tvContact,
            tvComplaint, tvBpCount, tvHrCount, tvRespCount, tvTempCount, tvPainScoreCount, tvDischargeType;
    private ImageView ivHospitalLogo;
    private Preception perceptionData;
    private ArrayList<Medicine> medicineList;
    private ArrayList<Test> testList;
    private ArrayList<PTAdvisedModel> treatmentAdvisedList = new ArrayList<>();
    private ArrayList<PTGivenModel> treatmentGivenList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        showHomeBackOnToolbar(toolbar);

        initViews();
    }

    private void initViews() {
        opdData = (OpdList) getIntent().getParcelableExtra("OPD_DATA");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        rightDrawer = (RelativeLayout) findViewById(R.id.nav_right);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        tvPatientName = findViewById(R.id.tvPatientName);
        tvPatientId = findViewById(R.id.tvPatientId);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvHospitalName = findViewById(R.id.tvHospitalName);
        tvOpdCreatedDate = findViewById(R.id.tvOpdCreatedDate);
        tvAge = findViewById(R.id.tvAge);
        tvContact = findViewById(R.id.tvContact);
        ivHospitalLogo = findViewById(R.id.ivHospitalLogo);

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
        tvOpdCreatedDate.setText(changeDateFormat(opdData.getOpdCreatedDate()));


        String imgData = opdData.getHospitalImage();
        //imgData = imgData.replace("data:image/png;base64,","");

        Glide.with(mContext)
                .load(imgData)
                .into(ivHospitalLogo);

        /*try {
            byte[] imageBytes = Base64.decode(imgData, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
            ivHospitalLogo.setImageBitmap(decodedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
                        perceptionData = detailModel.getPreception();
                        medicineList = (ArrayList<Medicine>) perceptionData.getMedicine();
                        testList = (ArrayList<Test>) perceptionData.getTest();
                        //tvComplaint, tvBpCount, tvHrCount, tvRespCount, tvTempCount, tvPainScoreCount
                        tvComplaint.setText(perceptionData.getOpdCheifComplaints());
                        tvBpCount.setText(perceptionData.getOpdBp());
                        tvHrCount.setText(perceptionData.getOpdHeartRatePerMin());
                        tvRespCount.setText(perceptionData.getOpdRespRateMin());
                        tvTempCount.setText(perceptionData.getOpdTemp());
                        tvPainScoreCount.setText(perceptionData.getOpdPainScore());
                        tvDischargeType.setText(perceptionData.getOpdTypeOfDischarge());

                        treatmentAdvisedList.clear();
                        treatmentGivenList.clear();

                        for (int i = 0; i<medicineList.size(); i++){
                            PTGivenModel givenModel = new PTGivenModel();
                            PTAdvisedModel advisedModel = new PTAdvisedModel();
                            Medicine medicine = medicineList.get(i);
                            if (medicine.getPreceptionType().equals("0")){
                                givenModel.setPrescriptionTreatmentType("Medicine");
                                if (medicine.getPreception().equals(0)){
                                    givenModel.setPrescriptionContentType("Image");
                                }else{
                                    givenModel.setPrescriptionContentType("Text");
                                }
                                givenModel.setOpdPrescriptionId(medicine.getOpdPreceptionId());
                                givenModel.setMedicineId(medicine.getMedicineId());
                                givenModel.setMedicineName(medicine.getMedicineName());
                                givenModel.setMedicineDoes(medicine.getMedicineDose());
                                givenModel.setOpdPrescriptionCreatedDate(medicine.getOpdPreceptionCreatedDate());

                                treatmentGivenList.add(givenModel);
                            }else{
                                advisedModel.setPrescriptionTreatmentType("Medicine");
                                if (medicine.getPreception().equals(0)){
                                    advisedModel.setPrescriptionContentType("Image");
                                }else{
                                    advisedModel.setPrescriptionContentType("Text");
                                }
                                advisedModel.setOpdPrescriptionId(medicine.getOpdPreceptionId());
                                advisedModel.setMedicineId(medicine.getMedicineId());
                                advisedModel.setMedicineName(medicine.getMedicineName());
                                advisedModel.setMedicineDoes(medicine.getMedicineDose());
                                advisedModel.setOpdPrescriptionCreatedDate(medicine.getOpdPreceptionCreatedDate());

                                treatmentAdvisedList.add(advisedModel);
                            }
                        }

                        for (int i = 0; i<testList.size(); i++){
                            PTGivenModel givenModel = new PTGivenModel();
                            PTAdvisedModel advisedModel = new PTAdvisedModel();
                            Test test = testList.get(i);
                            if (test.getOpdPathologyTestType().equals("0")){
                                givenModel.setPrescriptionTreatmentType("Test");
                                if (test.getPreception().equals(0)){
                                    givenModel.setPrescriptionContentType("Image");
                                }else{
                                    givenModel.setPrescriptionContentType("Text");
                                }
                                givenModel.setOpdPathologyTestId(test.getOpdPathologyTestId());
                                givenModel.setPathologyId(test.getPathologyId());
                                givenModel.setTestName(test.getTestName());
                                givenModel.setOpdPathologyCreatedDate(test.getOpdPathologyTestCreatedDate());

                                treatmentGivenList.add(givenModel);

                            }else{
                                advisedModel.setPrescriptionTreatmentType("Test");
                                if (test.getPreception().equals(0)){
                                    advisedModel.setPrescriptionContentType("Image");
                                }else{
                                    advisedModel.setPrescriptionContentType("Text");
                                }
                                advisedModel.setOpdPathologyTestId(test.getOpdPathologyTestId());
                                advisedModel.setPathologyId(test.getPathologyId());
                                advisedModel.setTestName(test.getTestName());
                                advisedModel.setOpdPathologyCreatedDate(test.getOpdPathologyTestCreatedDate());

                                treatmentAdvisedList.add(advisedModel);
                            }
                        }

                        initPrescriptionList();

                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bill_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_view){
            drawer.openDrawer(Gravity.END);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initPrescriptionList() {
        RecyclerView rvTrtGiven = findViewById(R.id.rvTrtGiven);
        RecyclerView rvTrtAdvised = findViewById(R.id.rvTrtAdvised);

        rvTrtGiven.setLayoutManager(new LinearLayoutManager(mContext));
        PrescriptionGivenListAdapter givenAdapter = new PrescriptionGivenListAdapter(treatmentGivenList, mContext);
        rvTrtGiven.setAdapter(givenAdapter);
        givenAdapter.notifyDataSetChanged();

        rvTrtAdvised.setLayoutManager(new LinearLayoutManager(mContext));
        PrescriptionAdvisedListAdapter advisedAdapter = new PrescriptionAdvisedListAdapter(treatmentAdvisedList, mContext);
        rvTrtAdvised.setAdapter(advisedAdapter);
        advisedAdapter.notifyDataSetChanged();
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
        }
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}
