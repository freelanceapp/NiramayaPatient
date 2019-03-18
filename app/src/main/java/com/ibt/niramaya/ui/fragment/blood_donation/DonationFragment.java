package com.ibt.niramaya.ui.fragment.blood_donation;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.Calendar;

public class DonationFragment extends BaseFragment {
    private View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_donation, container, false);
        mContext = getActivity();
        init();
        return rootview;
    }

    private void init() {
        ((Button)rootview.findViewById(R.id.btnBecomeDonor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonorDialogOne();
            }
        });
    }

    private void openDonorDialogOne() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_become_doner_one, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ((EditText) dialogView.findViewById(R.id.etDOB)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(((EditText) dialogView.findViewById(R.id.etDOB)));
            }
        });

        ((EditText) dialogView.findViewById(R.id.etLastDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(((EditText) dialogView.findViewById(R.id.etLastDate)));
            }
        });

        ((Button) dialogView.findViewById(R.id.btnNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialogOneData(dialogView, alertDialog);
            }
        });

        alertDialog.show();
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

                if (day <= 9) {
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
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("");
        dialog.show();
    }

    private void initDialogOneData(View dialogView, AlertDialog alertDialog) {
        String dName, dEmail, dMobile, dAlternateMobile, dDOB, dGender, dBloodGroup,
                dWeight, dAddress, dLastDate;
        dName = ((EditText) dialogView.findViewById(R.id.etName)).getText().toString().trim();
        dEmail = ((EditText) dialogView.findViewById(R.id.etEmail)).getText().toString().trim();
        dMobile = ((EditText) dialogView.findViewById(R.id.etMobile)).getText().toString().trim();
        dAlternateMobile = ((EditText) dialogView.findViewById(R.id.etAlternateMobile)).getText().toString().trim();
        dDOB = ((EditText) dialogView.findViewById(R.id.etDOB)).getText().toString().trim();
        dGender = ((EditText) dialogView.findViewById(R.id.etGender)).getText().toString().trim();
        dBloodGroup = ((EditText) dialogView.findViewById(R.id.etBloodGroup)).getText().toString().trim();
        dWeight = ((EditText) dialogView.findViewById(R.id.etWeight)).getText().toString().trim();
        dAddress = ((EditText) dialogView.findViewById(R.id.etAddress)).getText().toString().trim();
        dLastDate = ((EditText) dialogView.findViewById(R.id.etLastDate)).getText().toString().trim();

        if (dName.isEmpty()){
            showToast("Name should not be empty.");
        }else if(dEmail.isEmpty()){
            showToast("Email should not be empty.");
        }else if(dMobile.isEmpty()){
            showToast("Mobile should not be empty.");
        }else if(dDOB.isEmpty()){
            showToast("Date of Birth should not be empty.");
        }else if(dGender.isEmpty()){
            showToast("Date of Birth should not be empty.");
        }else if(dBloodGroup.isEmpty()){
            showToast("Blood Group should not be empty.");
        }else if(dWeight.isEmpty()){
            showToast("Weight should not be empty.");
        }else if(dAddress.isEmpty()){
            showToast("Address should not be empty.");
        }else if(dLastDate.isEmpty()){
            showToast("Last Date should not be empty.");
        }else{
            openDonorDialogTwo();
            alertDialog.dismiss();
        }
    }

    private void openDonorDialogTwo() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setCancelable(false);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_become_doner_two, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        ((Button)dialogView.findViewById(R.id.btnDialogBecomeDonor)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
