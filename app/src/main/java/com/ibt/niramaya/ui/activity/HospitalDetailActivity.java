package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.home.HospitalDatum;
import com.ibt.niramaya.modal.hospital_detail.DoctorDatum;
import com.ibt.niramaya.modal.hospital_detail.HospitalDetailModel;
import com.ibt.niramaya.modal.hospital_detail.HospitalSpecialization;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.fragment.home_detail_fragment.DepartmentsFragment;
import com.ibt.niramaya.ui.fragment.home_detail_fragment.DoctorFragment;
import com.ibt.niramaya.ui.fragment.home_detail_fragment.FacilitiesFragment;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.FragmentUtils;

import java.util.ArrayList;

import retrofit2.Response;

public class HospitalDetailActivity extends BaseActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private FragmentUtils fragmentUtils;
    public static String hospitalId;
    private HospitalDatum hospitalData;

    private ArrayList<DoctorDatum> doctorDataList = new ArrayList<>();
    private ArrayList<HospitalSpecialization> hospitalSpecializationList = new ArrayList<>();
    private String hospitalFacilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);

        init();
    }

    private void init() {

        if (getIntent()!=null) {
            try {
                hospitalData = getIntent().getExtras().getParcelable("HospitalData");
                hospitalId = hospitalData.getHospitalId();
                fetchHospitalDoctorList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        ImageView ivHospital = findViewById(R.id.ivHospital);
        TextView tvHospitalName= findViewById(R.id.tvHospitalName);
        TextView tvAddress= findViewById(R.id.tvAddress);

        Glide.with(mContext)
                .load(hospitalData.getHospialLogo())
                .fitCenter()
                .into(ivHospital);
        tvHospitalName.setText(hospitalData.getHospitalName());
        tvAddress.setText(hospitalData.getHospitalStreetName()+", "+hospitalData.getHospitalCity());

        ((TextView) findViewById(R.id.txtTitle)).setText("Hospital");
        findViewById(R.id.imgBack).setOnClickListener(this);

        findViewById(R.id.txtFacilities).setOnClickListener(this);
        findViewById(R.id.txtDepartments).setOnClickListener(this);
        findViewById(R.id.txtDoctors).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtFacilities:

                Bundle bundle = new Bundle();
                bundle.putString("FACILITIES", hospitalFacilities);
                FacilitiesFragment facilitiesFragment = new FacilitiesFragment();
                facilitiesFragment.setArguments(bundle);

                onTextviewClick(R.id.txtFacilities, R.id.txtDepartments, R.id.txtDoctors);
                fragmentUtils.replaceFragment(facilitiesFragment, Constant.FacilitiesFragment, R.id.frameLayout);

                break;
            case R.id.txtDepartments:

                if (hospitalSpecializationList.size()>0) {
                    Bundle dBundle = new Bundle();
                    dBundle.putParcelableArrayList("DEPARTMENTS", hospitalSpecializationList);
                    dBundle.putParcelable("HOSPITAL", hospitalData);
                    DepartmentsFragment departmentsFragment = new DepartmentsFragment();
                    departmentsFragment.setArguments(dBundle);

                    onTextviewClick(R.id.txtDepartments, R.id.txtFacilities, R.id.txtDoctors);
                    fragmentUtils.replaceFragment(departmentsFragment, Constant.DepartmentsFragment, R.id.frameLayout);
                }

                break;
            case R.id.txtDoctors:

                if (doctorDataList.size()>0) {
                    Bundle dctBundle = new Bundle();
                    dctBundle.putParcelableArrayList("DOCTORS", doctorDataList);
                    dctBundle.putString("HospitalId", hospitalId);
                    DoctorFragment doctorFragment = new DoctorFragment();
                    doctorFragment.setArguments(dctBundle);

                    onTextviewClick(R.id.txtDoctors, R.id.txtFacilities, R.id.txtDepartments);
                    fragmentUtils.replaceFragment(doctorFragment, Constant.DoctorFragment, R.id.frameLayout);
                }

                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }

    private void onTextviewClick(int a, int b, int c) {
        ((TextView) findViewById(a)).setBackground(getResources().getDrawable(R.drawable.layout_bg5));
        ((TextView) findViewById(b)).setBackground(getResources().getDrawable(R.drawable.layout_bg14));
        ((TextView) findViewById(c)).setBackground(getResources().getDrawable(R.drawable.layout_bg14));

        ((TextView) findViewById(a)).setTextColor(getResources().getColor(R.color.white));
        ((TextView) findViewById(b)).setTextColor(getResources().getColor(R.color.text_color_c));
        ((TextView) findViewById(c)).setTextColor(getResources().getColor(R.color.text_color_c));
    }

    private void fetchHospitalDoctorList() {
        String userId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
        if (cd.isNetworkAvailable()){
            RetrofitService.getOpdDoctorList(new Dialog(mContext), retrofitApiClient.doctorOpdList(
                    hospitalId, userId, ""), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    HospitalDetailModel hospitalDetail = (HospitalDetailModel)  result.body();
                    if (!hospitalDetail.getError()){
                        if (hospitalDetail.getDoctorData().size()>0){
                            doctorDataList = (ArrayList<DoctorDatum>) hospitalDetail.getDoctorData();
                        }
                        if (hospitalDetail.getHospitalSpecialization().size()>0){
                            hospitalSpecializationList = (ArrayList<HospitalSpecialization>) hospitalDetail.getHospitalSpecialization();
                        }
                        hospitalFacilities = hospitalDetail.getHospitalFacilities();
                        Bundle bundle = new Bundle();
                        bundle.putString("FACILITIES", hospitalFacilities);
                        fragmentManager = getSupportFragmentManager();
                        fragmentUtils = new FragmentUtils(fragmentManager);
                        FacilitiesFragment facilitiesFragment = new FacilitiesFragment();
                        facilitiesFragment.setArguments(bundle);
                        fragmentUtils.replaceFragment(facilitiesFragment, Constant.FacilitiesFragment, R.id.frameLayout);
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }
}
