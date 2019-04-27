package com.ibt.niramaya.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class PatientRagistrationActivity extends BaseActivity implements View.OnClickListener {
    private ConnectionDetector cd;
    private int selectedPosition = 0;
    private ArrayAdapter bloodGroupAdapter, relationshipStatusAdapter;
    private String[] bloodGroupList = {"Select blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private String[] relationshipList = {"Select Relationship", "Father", "Mother", "Child", "Wife"};
    private String strBloodGroup = "", strRelationship = "", strGender = "", strmobile = "";
    private EditText patientName, aadharNumber, patientEmailId, patientDateofBirthNumber, patienthouseNo,
            patientSTreet, patientCity, patientState, patientCountry, patientZipCode, patientGardian, patientRelation, patientGardianContact, patientGardianAddress;
    private TextView patientMobileNumber;
    private ImageView imgProfilePatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_ragistration);
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        findId();
        spinnerData();
        radioGroupData();
    }

    private void radioGroupData() {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rdGroupGender);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                strGender = rb.getText().toString();
            }
        });
    }

    private void findId() {
        patientName = findViewById(R.id.patientName);
        patientMobileNumber = findViewById(R.id.patientMobileNumber);
        aadharNumber = findViewById(R.id.aadharNumber);
        patientEmailId = findViewById(R.id.patientEmailId);
        patientDateofBirthNumber = findViewById(R.id.patientDateofBirthNumber);
        patienthouseNo = findViewById(R.id.patienthouseNo);
        patientSTreet = findViewById(R.id.patientSTreet);
        patientCity = findViewById(R.id.patientCity);
        patientState = findViewById(R.id.patientState);
        patientCountry = findViewById(R.id.patientCountry);
        patientZipCode = findViewById(R.id.patientZipCode);
        patientGardian = findViewById(R.id.patientGardian);
        patientRelation = findViewById(R.id.patientRelation);
        patientGardianContact = findViewById(R.id.patientGardianContact);
        patientGardianAddress = findViewById(R.id.patientGardianAddress);
        imgProfilePatient = findViewById(R.id.imgProfilePatient);


        patientDateofBirthNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(patientDateofBirthNumber);
            }
        });

        strmobile = AppPreference.getStringPreference(mContext, Constant.USER_CONTACT);
        patientMobileNumber.setText(strmobile);

        ((Button) findViewById(R.id.ragisterPatient)).setOnClickListener(this);
    }

    private void spinnerData() {
        /********************************************************
         * Spinner blood group select
         * +++++++++++++++++++++++++++++++++++++++++++++++++++*/
        Spinner spnBloodGroup = findViewById(R.id.spinnerBloodGroup);
        bloodGroupAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, bloodGroupList);
        spnBloodGroup.setAdapter(bloodGroupAdapter);
        spnBloodGroup.setSelection(selectedPosition);
        spnBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBloodGroup = bloodGroupList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bloodGroupAdapter.notifyDataSetChanged();

        /*******************************************************
         *      Spinner Relationship status
         * *****************************************************/
        Spinner spnRelationshipstatus = findViewById(R.id.spinnerRelationship);
        relationshipStatusAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, relationshipList);
        spnRelationshipstatus.setAdapter(relationshipStatusAdapter);
        spnRelationshipstatus.setSelection(selectedPosition);
        spnRelationshipstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strRelationship = relationshipList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        relationshipStatusAdapter.notifyDataSetChanged();
    }

    private void openDatePicker(final EditText etDate) {
        int dobYear = Calendar.getInstance().get(Calendar.YEAR);
        int dobMonth = Calendar.getInstance().get(Calendar.MONTH);
        int dobDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(mContext, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String sDay;
                String sMonth;

                if (day <= 2) {
                    sDay = "0" + day;
                } else {
                    sDay = String.valueOf(day);
                }
                if ((month + 1) <= 9) {
                    sMonth = "0" + (month + 1);
                } else {
                    sMonth = String.valueOf((month + 1));
                }

                etDate.setText(sDay + "/" + sMonth + "/" + year);

            }
        }, dobYear, dobMonth, dobDay);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("");
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ragisterPatient:
                createPatientRagisterApi();
                break;
        }
    }

    private void createPatientRagisterApi() {
        if (cd.isNetworkAvailable()) {


            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String strName = patientName.getText().toString();
            String strAadahr = aadharNumber.getText().toString();
            String strEmailadd = patientEmailId.getText().toString();
            String strDob = patientDateofBirthNumber.getText().toString();
            patientDateofBirthNumber.getText().toString();
            String strHouseNo = patienthouseNo.getText().toString();
            String strStreet = patientSTreet.getText().toString();
            String strCity = patientCity.getText().toString();
            String strState = patientState.getText().toString();
            String strCountry = patientCountry.getText().toString();
            String strZipcode = patientZipCode.getText().toString();
            String strGardian = patientGardian.getText().toString();
            String strRelation = patientRelation.getText().toString();
            String strGardianContact = patientGardianContact.getText().toString();
            String strGarAddress = patientGardianAddress.getText().toString();

            if (strName.isEmpty()) {
                Alerts.show(mContext, "Patient name should not be empty!!!");
            } else if (strAadahr.isEmpty()) {
                Alerts.show(mContext, "Aadhar number should not be empty!!!");
            } else if (strEmailadd.isEmpty()) {
                Alerts.show(mContext, "Email address should not be empty!!!");
            } else if (strDob.isEmpty()) {
                Alerts.show(mContext, "Date of birth should not be empty!!!");
            } else if (strCity.isEmpty()) {
                Alerts.show(mContext, "City should not be empty!!!");
            } else if (strState.isEmpty()) {
                Alerts.show(mContext, "State name should not be empty!!!");
            } else if (strCountry.isEmpty()) {
                Alerts.show(mContext, "Country name should not be empty!!!");
            } else if (strZipcode.isEmpty()) {
                Alerts.show(mContext, "Zipcode should not be empty!!!");
            } else if (strGardian.isEmpty()) {
                Alerts.show(mContext, "Gardian namem should not be empty!!!");
            } else if (strGardianContact.isEmpty()) {
                Alerts.show(mContext, "Gardian name should not be empty!!!");
/*
                strName,strBloodGroup,
                        strmobile,strDob,strEmailadd,strHouseNo,strStreet,strCity,strState,strCountry,strZipcode,strGender,
                        strGardian,strRelation,strGardianContact,strGarAddress,strAadahr,"",strRelationship,""*/
            } else {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.createPatientProfie(strName, strBloodGroup,
                        strmobile, strDob, strEmailadd, strHouseNo, strStreet, strCity, strState, strCountry, strZipcode, strGender,
                        strGardian, strRelation, strGardianContact, strGarAddress, strAadahr, strUserId, strRelationship, ""), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.toString());
                            } else {
                                Alerts.show(mContext, jsonObject.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onResponseFailed(String error) {
                        Alerts.show(mContext, error);
                    }
                });
            }
        }
    }
}
