package com.ibt.niramaya.ui.activity.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.ConnectionDetector;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientDetailActivity extends BaseActivity {
    private ConnectionDetector cd;
    private CircleImageView imgProfilePatient;
    private TextView tvPatientName, tvPatientMobileNumber, tvPatientaadharNumber, tvPatientEmailId, tvPatientDateofBirthNumber, tvPatientGender, tvPatientbloodGroup, tvPatienthouseNo, tvPatientCity, tvPatientState, tvPatientCountry, tvPatientZipCode, tvPatientGardian, tvPatientRelationship, tvPatientGardianContact, tvGardianAddress, tvPatientRelationshipStatus;
    private PaitentProfile paitentProfileData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbarPatientDetail);
        toolbar.setTitle("Patient Detail");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findId();
        getIntentData();
    }

    private void findId() {
        imgProfilePatient = findViewById(R.id.imgProfilePatient);
        tvPatientName = findViewById(R.id.tvPatientName);
        tvPatientMobileNumber = findViewById(R.id.tvPatientMobileNumber);
        tvPatientaadharNumber = findViewById(R.id.tvPatientaadharNumber);
        tvPatientEmailId = findViewById(R.id.tvPatientEmailId);
        tvPatientDateofBirthNumber = findViewById(R.id.tvPatientDateofBirthNumber);
        tvPatientGender = findViewById(R.id.tvPatientGender);
        tvPatientbloodGroup = findViewById(R.id.tvPatientbloodGroup);
        tvPatienthouseNo = findViewById(R.id.tvPatienthouseNo);
        tvPatientCity = findViewById(R.id.tvPatientCity);
        tvPatientState = findViewById(R.id.tvPatientState);
        tvPatientCountry = findViewById(R.id.tvPatientCountry);
        tvPatientZipCode = findViewById(R.id.tvPatientZipCode);
        tvPatientGardian = findViewById(R.id.tvPatientGardian);
        tvPatientRelationship = findViewById(R.id.tvPatientRelationship);
        tvPatientGardianContact = findViewById(R.id.tvPatientGardianContact);
        tvGardianAddress = findViewById(R.id.tvGardianAddress);
        tvPatientRelationshipStatus = findViewById(R.id.tvPatientRelationshipStatus);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        paitentProfileData = (PaitentProfile) intent.getParcelableExtra("patientDetail");
        if (paitentProfileData != null) {
            Glide.with(mContext).load(paitentProfileData.getPatientProfilePicture()).into(imgProfilePatient);
            tvPatientName.setText(paitentProfileData.getPatientName());
            tvPatientMobileNumber.setText(paitentProfileData.getPatientContact());
            tvPatientaadharNumber.setText(paitentProfileData.getPatientAadharNumber());
            tvPatientEmailId.setText(paitentProfileData.getPatientEmail());
            tvPatientDateofBirthNumber.setText(paitentProfileData.getPatientDateOfBirth());
            tvPatientGender.setText(paitentProfileData.getPatientGender());
            tvPatientbloodGroup.setText(paitentProfileData.getPatientBloodgroup());
            tvPatienthouseNo.setText(paitentProfileData.getPatientHouseNumber());
            tvPatientCity.setText(paitentProfileData.getPatientCity());
            tvPatientState.setText(paitentProfileData.getPatientState());
            tvPatientCountry.setText(paitentProfileData.getPatientCountry());
            tvPatientZipCode.setText(paitentProfileData.getPatientZipcode());
            tvPatientGardian.setText(paitentProfileData.getPatientGardianName());
            tvPatientRelationship.setText(paitentProfileData.getPatientRelationshipWithGardian());
            tvPatientGardianContact.setText(paitentProfileData.getPatientGardianContact());
            tvGardianAddress.setText(paitentProfileData.getPatientGardianAddress());
            tvPatientRelationshipStatus.setText(paitentProfileData.getRelationshipStatus());
        } else {
            Alerts.show(mContext, "There is no Data");
        }
    }

}
