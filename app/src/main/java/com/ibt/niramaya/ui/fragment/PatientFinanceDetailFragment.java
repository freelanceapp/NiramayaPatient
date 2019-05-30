package com.ibt.niramaya.ui.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ibt.niramaya.R;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.Calendar;

public class PatientFinanceDetailFragment extends BaseFragment {
    private View rootView;
    private EditText etPolicyTitle, etPolicyProviderName, etPolicyNumber, etPolicyValidity, etAttachFile;
    private RadioGroup rgPolicyStatus;
    private RadioButton rbActive, rbDActive;
    private String _path;
    private File reportFile;
    private static final int LOAD_IMAGE_GALLERY = 123;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_patient_finance_detail, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        initViews();
        return rootView;
    }

    private void initViews() {
        etPolicyTitle = rootView.findViewById(R.id.etPolicyTitle);
        etPolicyProviderName = rootView.findViewById(R.id.etPolicyProviderName);
        etPolicyNumber = rootView.findViewById(R.id.etPolicyNumber);
        etPolicyValidity = rootView.findViewById(R.id.etPolicyValidity);
        etAttachFile = rootView.findViewById(R.id.etAttachFile);

        //int selectedId = radioSexGroup.getCheckedRadioButtonId();

        ((Button) rootView.findViewById(R.id.btnSubmit)).setOnClickListener((v -> submitPolicy()));

        etPolicyValidity.setOnClickListener(v -> openDatePicker());
        etAttachFile.setOnClickListener((v) -> openFilePicker());
    }

    private void submitPolicy() {
        
    }

    private void openFilePicker() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
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
}
