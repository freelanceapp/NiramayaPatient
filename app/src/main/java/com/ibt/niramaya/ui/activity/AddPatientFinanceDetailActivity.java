package com.ibt.niramaya.ui.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.finance.PatientFinance;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;
import com.obsez.android.lib.filechooser.ChooserDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AddPatientFinanceDetailActivity extends BaseActivity {

    private EditText etPolicyTitle, etPolicyProviderName, etPolicyNumber, etPolicyValidity, etAttachFile;
    private RadioGroup rgPolicyStatus;
    private RadioButton rbActive, rbDActive;
    private Button btnSubmit;
    private String _path;
    private File reportFile;
    private static final int LOAD_IMAGE_GALLERY = 123;
    private String from;
    private PatientFinance financeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_finance_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
    }

    private void initViews() {
        etPolicyTitle = findViewById(R.id.etPolicyTitle);
        etPolicyProviderName = findViewById(R.id.etPolicyProviderName);
        etPolicyNumber = findViewById(R.id.etPolicyNumber);
        etPolicyValidity = findViewById(R.id.etPolicyValidity);
        etAttachFile = findViewById(R.id.etAttachFile);

        rgPolicyStatus = findViewById(R.id.rgPolicyStatus);
        rbActive = findViewById(R.id.rbActive);
        rbDActive = findViewById(R.id.rbDActive);
        btnSubmit = findViewById(R.id.btnSubmit);


        etPolicyValidity.setOnClickListener(v -> openDatePicker());
        etAttachFile.setOnClickListener((v) -> openFilePicker());

        from = getIntent().getExtras().getString("FROM");
        if (from.equals("UPDATE")) {
            financeData = getIntent().getParcelableExtra("FinanceData");
            etPolicyTitle.setText(financeData.getFinanceTitle());
            etPolicyProviderName.setText(financeData.getFinanceProvider());
            etPolicyNumber.setText(financeData.getFinancePolicyNumber());
            etPolicyValidity.setText(financeData.getFinancePolicyValidTime());
            if (financeData.getFinancePolicyStatus().equals("1")){
                rgPolicyStatus.check(R.id.rbActive);
            }else if (financeData.getFinancePolicyStatus().equals("0")){
                rgPolicyStatus.check(R.id.rbDActive);
            }
            btnSubmit.setText("Update");
            ((TextView) findViewById(R.id.lblTitle)).setText("Update Patient Finance Detail");
        }


        btnSubmit.setOnClickListener((v -> {
            if (from.equals("UPDATE")) {
                updatePolicy();
            } else {
                submitPolicy();
            }
        }));
    }

    private void openFilePicker() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddPatientFinanceDetailActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, LOAD_IMAGE_GALLERY);
        } else {
            openFileChooser();
        }
    }

    private void openFileChooser() {
        //Alerts.show(mContext, "Select $type");
        new ChooserDialog(mContext)
                .withFilter(false, false, "jpg", "jpeg", "png", "pdf", "doc", "docx",
                        "odt", "ods", "xls", "xlsx", "txt")
                .withStartFile(_path)
                .withChosenListener((path, pathFile) -> {
                    String ext = path.substring(path.lastIndexOf("."));
                    reportFile = pathFile;
                    etAttachFile.setText(reportFile.getName());
                    Log.e("File ", ext);
                    //uploadTestReport();
                })
                // to handle the back key pressed or clicked outside the dialog:
                .withOnCancelListener(dialog -> {
                    Log.d("CANCEL", "CANCEL");
                    dialog.cancel(); // MUST have
                })
                .build()
                .show();
    }


    private void openDatePicker() {
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

                etPolicyValidity.setText(sDay + "/" + sMonth + "/" + year);

            }
        }, dobYear, dobMonth, dobDay);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("");
        dialog.show();
    }

    private void submitPolicy() {
        String policyTitle, policyProvider, policyRegNumber, policyValidity, policyStatus;
        int radioId = 0;
        String patientFinanceId, patientId;

        patientFinanceId = "0";
        patientId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);

        policyTitle = etPolicyTitle.getText().toString().trim();
        policyProvider = etPolicyProviderName.getText().toString().trim();
        policyRegNumber = etPolicyNumber.getText().toString().trim();
        policyValidity = etPolicyValidity.getText().toString().trim();

        try {
            radioId = rgPolicyStatus.getCheckedRadioButtonId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (radioId == R.id.rbActive) {
            policyStatus = "1";
        } else if (radioId == R.id.rbDActive) {
            policyStatus = "0";
        } else {
            policyStatus = "";
        }

        if (policyTitle.isEmpty()) {
            Alerts.show(mContext, "Policy title should not be empty");
        } else if (policyProvider.isEmpty()) {
            Alerts.show(mContext, "Policy provider should not be empty");
        } else if (policyRegNumber.isEmpty()) {
            Alerts.show(mContext, "Policy reg. number should not be empty");
        } else if (policyValidity.isEmpty()) {
            Alerts.show(mContext, "Policy validity should not be empty");
        } else if (policyStatus.isEmpty()) {
            Alerts.show(mContext, "Policy status not selected");
        } else if (reportFile == null) {
            Alerts.show(mContext, "You have to attach a policy file copy");
        } else {

            RequestBody pFId = RequestBody.create(MediaType.parse("text/plain"), patientFinanceId);
            RequestBody pId = RequestBody.create(MediaType.parse("text/plain"), patientId);

            RequestBody pTitle = RequestBody.create(MediaType.parse("text/plain"), policyTitle);
            RequestBody pProvider = RequestBody.create(MediaType.parse("text/plain"), policyProvider);
            RequestBody pRegNumber = RequestBody.create(MediaType.parse("text/plain"), policyRegNumber);
            RequestBody pValidity = RequestBody.create(MediaType.parse("text/plain"), serverDateFormat(policyValidity));
            RequestBody pStatus = RequestBody.create(MediaType.parse("text/plain"), policyStatus);

            RequestBody trFile = RequestBody.create(MediaType.parse("image/*"), reportFile);
            MultipartBody.Part reportFilePart = MultipartBody.Part.createFormData("policy_document", reportFile.getName(), trFile);

            if (cd.isNetworkAvailable()) {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.addPatientFinanceReprot(
                        pFId, pId, pTitle, pProvider, pRegNumber, pValidity, pStatus, reportFilePart), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                onBackPressed();
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {

                    }
                });
            }
        }
    }

    private void updatePolicy() {
        String policyTitle, policyProvider, policyRegNumber, policyValidity, policyStatus;
        int radioId = 0;
        String patientFinanceId, patientId;

        patientFinanceId = financeData.getPatientFinanceId();
        patientId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);

        policyTitle = etPolicyTitle.getText().toString().trim();
        policyProvider = etPolicyProviderName.getText().toString().trim();
        policyRegNumber = etPolicyNumber.getText().toString().trim();
        policyValidity = etPolicyValidity.getText().toString().trim();

        try {
            radioId = rgPolicyStatus.getCheckedRadioButtonId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (radioId == R.id.rbActive) {
            policyStatus = "1";
        } else if (radioId == R.id.rbDActive) {
            policyStatus = "0";
        } else {
            policyStatus = "";
        }

        if (policyTitle.isEmpty()) {
            Alerts.show(mContext, "Policy title should not be empty");
        } else if (policyProvider.isEmpty()) {
            Alerts.show(mContext, "Policy provider should not be empty");
        } else if (policyRegNumber.isEmpty()) {
            Alerts.show(mContext, "Policy reg. number should not be empty");
        } else if (policyValidity.isEmpty()) {
            Alerts.show(mContext, "Policy validity should not be empty");
        } else if (policyStatus.isEmpty()) {
            Alerts.show(mContext, "Policy status not selected");
        } else {

            RequestBody pFId = RequestBody.create(MediaType.parse("text/plain"), patientFinanceId);
            RequestBody pId = RequestBody.create(MediaType.parse("text/plain"), patientId);

            RequestBody pTitle = RequestBody.create(MediaType.parse("text/plain"), policyTitle);
            RequestBody pProvider = RequestBody.create(MediaType.parse("text/plain"), policyProvider);
            RequestBody pRegNumber = RequestBody.create(MediaType.parse("text/plain"), policyRegNumber);
            RequestBody pValidity = RequestBody.create(MediaType.parse("text/plain"), serverDateFormat(policyValidity));
            RequestBody pStatus = RequestBody.create(MediaType.parse("text/plain"), policyStatus);

            MultipartBody.Part reportFilePart = null;
            if (reportFile != null) {
                RequestBody trFile = RequestBody.create(MediaType.parse("image/*"), reportFile);
                reportFilePart = MultipartBody.Part.createFormData("policy_document", reportFile.getName(), trFile);
            }

            if (cd.isNetworkAvailable()) {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.addPatientFinanceReprot(
                        pFId, pId, pTitle, pProvider, pRegNumber, pValidity, pStatus, reportFilePart), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                Alerts.show(mContext, jsonObject.getString("message"));
                                onBackPressed();
                            } else {
                                Alerts.show(mContext, jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponseFailed(String error) {

                    }
                });
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String serverDateFormat(String time) {

        String inputPattern = "dd/MM/yyyy";
        String outputPattern = "yyyy-MM-dd";
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
}
