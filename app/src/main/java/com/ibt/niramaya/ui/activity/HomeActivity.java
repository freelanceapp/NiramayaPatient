package com.ibt.niramaya.ui.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.modal.patient_modal.PatientMainModal;
import com.ibt.niramaya.retrofit.RetrofitApiClient;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.ui.fragment.PatientFragment;
import com.ibt.niramaya.ui.fragment.BedFragment;
import com.ibt.niramaya.ui.fragment.DocumentsFragment;
import com.ibt.niramaya.ui.fragment.HomeFragment;
import com.ibt.niramaya.ui.fragment.InvoiceFragment;
import com.ibt.niramaya.ui.fragment.PrescriptionsFragment;
import com.ibt.niramaya.ui.fragment.ReportFragment;
import com.ibt.niramaya.ui.fragment.blood_donation.BloodDonationFragment;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.ConnectionDetector;
import com.ibt.niramaya.utils.FragmentUtils;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public RetrofitApiClient retrofitApiClient;
    public ConnectionDetector cd;
    public Context mContext;
    public static TextView txtTitle;
    public static ImageView imgSearch, imgSort;
    private SlidingRootNav slidingRootNav;
    private Spinner spnPatient;
    private CardView cvPatient;
    private FragmentUtils fragmentUtils;
    private FragmentManager fragmentManager;
    private List<PaitentProfile> patientList = new ArrayList<>();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = this;
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {

        imgSearch = findViewById(R.id.imgSearch);
        imgSort = findViewById(R.id.imgSort);
        txtTitle = findViewById(R.id.txtTitle);

        fragmentManager = getSupportFragmentManager();
        fragmentUtils = new FragmentUtils(fragmentManager);
        fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        spnPatient = findViewById(R.id.spnPatient);
        cvPatient = findViewById(R.id.cvPatient);

        Alerts.show(mContext, AppPreference.getStringPreference(mContext, Constant.FIREBASE_TOKEN));

        initPatientSpinner();

        clickListener();
    }

    private void initPatientSpinner() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            RetrofitService.getPatientList(new Dialog(mContext), retrofitApiClient.patientList(strUserId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    PatientMainModal mainModal = (PatientMainModal) result.body();
                    if (mainModal != null) {
                        patientList = mainModal.getUser().getPaitentProfile();

                        if (patientList.size() > 0) {
                            spnPatient.setVisibility(View.VISIBLE);
                            cvPatient.setVisibility(View.VISIBLE);
                        } else {
                            spnPatient.setVisibility(View.GONE);
                            cvPatient.setVisibility(View.GONE);
                        }

                        PaitentProfile paitentProfile1 = new PaitentProfile();
                        paitentProfile1.setPatientId("0");
                        paitentProfile1.setPatientName("Select Patient");
                        patientList.add(0, paitentProfile1);

                        ArrayAdapter aa = new ArrayAdapter(mContext, R.layout.row_spinner_item, patientList);
                        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spnPatient.setAdapter(aa);
                        spnPatient.setOnItemSelectedListener(spinnerListener);
                    } else {
                        Alerts.show(mContext, mainModal.getMessage());
                    }
                }

                @Override
                public void onResponseFailed(String error) {
                    Alerts.show(mContext, error);
                }
            });
        }
    }

    private void clickListener() {
        findViewById(R.id.txtHome).setOnClickListener(this);
        findViewById(R.id.txtPrescription).setOnClickListener(this);
        findViewById(R.id.txtReports).setOnClickListener(this);
        findViewById(R.id.txtInvoice).setOnClickListener(this);
        findViewById(R.id.txtBed).setOnClickListener(this);
        findViewById(R.id.txtHistory).setOnClickListener(this);
        findViewById(R.id.txtBloodDonation).setOnClickListener(this);
        findViewById(R.id.txtDocuments).setOnClickListener(this);
        findViewById(R.id.txtSettings).setOnClickListener(this);
        findViewById(R.id.txtAddUser).setOnClickListener(this);
        findViewById(R.id.llLogout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment AddUserFragment = fragmentManager.findFragmentByTag(Constant.AddUserFragment);
        Fragment BloodDonationFragment = fragmentManager.findFragmentByTag(Constant.BloodDonationFragment);
        Fragment BedFragment = fragmentManager.findFragmentByTag(Constant.BedFragment);
        Fragment HomeFragment = fragmentManager.findFragmentByTag(Constant.HomeFragment);
        Fragment InvoiceFragment = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);
        Fragment DocumentsFragment = fragmentManager.findFragmentByTag(Constant.DocumentsFragment);
        Fragment PrescriptionFragment = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment ReportFragment = fragmentManager.findFragmentByTag(Constant.ReportsFragment);

        String patientId = AppPreference.getStringPreference(mContext, Constant.CURRENT_PATENT_ID);

        switch (v.getId()) {
            case R.id.txtHome:
                txtTitle.setText("Home");
                if (HomeFragment == null) {
                    fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);
                    slidingRootNav.closeMenu();
                }
                break;
            case R.id.txtPrescription:
                txtTitle.setText("Prescription");
                if (PrescriptionFragment == null) {
                    if (!patientId.isEmpty() && !patientId.equals("0")) {
                        fragmentUtils.replaceFragment(new PrescriptionsFragment(), Constant.PrescriptionFragment, R.id.home_frame);
                        slidingRootNav.closeMenu();
                    } else {
                        Alerts.show(mContext, "No Patient Selected!");
                    }
                }
                break;
            case R.id.txtReports:
                txtTitle.setText("Report");
                if (ReportFragment == null) {
                    if (!patientId.isEmpty() && !patientId.equals("0")) {
                        fragmentUtils.replaceFragment(new ReportFragment(), Constant.ReportsFragment, R.id.home_frame);
                        slidingRootNav.closeMenu();
                    } else {
                        Alerts.show(mContext, "No Patient Selected!");
                    }
                }
                break;
            case R.id.txtInvoice:
                txtTitle.setText("Invoice");
                if (InvoiceFragment == null) {
                    if (!patientId.isEmpty() && !patientId.equals("0")) {
                        fragmentUtils.replaceFragment(new InvoiceFragment(), Constant.InvoiceFragment, R.id.home_frame);
                        slidingRootNav.closeMenu();
                    } else {
                        Alerts.show(mContext, "No Patient Selected!");
                    }
                }
                break;
            case R.id.txtBed:
                txtTitle.setText("Bed History");
                if (BedFragment == null) {
                    fragmentUtils.replaceFragment(new BedFragment(), Constant.BedFragment, R.id.home_frame);
                    slidingRootNav.closeMenu();
                }
                break;
            case R.id.txtHistory:
                break;
            case R.id.txtBloodDonation:
                txtTitle.setText("Blood Donation");
                if (BloodDonationFragment == null) {
                    fragmentUtils.replaceFragment(new BloodDonationFragment(), Constant.BloodDonationFragment, R.id.home_frame);
                    slidingRootNav.closeMenu();
                }
                break;
            case R.id.txtDocuments:
                txtTitle.setText("Documents");
                if (DocumentsFragment == null) {
                    fragmentUtils.replaceFragment(new DocumentsFragment(), Constant.DocumentsFragment, R.id.home_frame);
                    slidingRootNav.closeMenu();
                }
                break;
            case R.id.txtSettings:
                break;
            case R.id.txtAddUser:
                txtTitle.setText("Add Patient");
                if (AddUserFragment == null) {
                    fragmentUtils.replaceFragment(new PatientFragment(), Constant.AddUserFragment, R.id.home_frame);
                    slidingRootNav.closeMenu();
                }
                break;
            case R.id.llLogout:
                doLogout();
                slidingRootNav.closeMenu();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment AddUserFragment = fragmentManager.findFragmentByTag(Constant.AddUserFragment);
        Fragment BedFragment = fragmentManager.findFragmentByTag(Constant.BedFragment);
        Fragment HomeFragment = fragmentManager.findFragmentByTag(Constant.HomeFragment);
        Fragment InvoiceFragment = fragmentManager.findFragmentByTag(Constant.InvoiceFragment);
        Fragment PrescriptionFragment = fragmentManager.findFragmentByTag(Constant.PrescriptionFragment);
        Fragment ReportFragment = fragmentManager.findFragmentByTag(Constant.ReportsFragment);

        if (HomeFragment != null) {
            finish();
        } else if (PrescriptionFragment != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);
            slidingRootNav.closeMenu();
        } else if (ReportFragment != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);
            slidingRootNav.closeMenu();
        } else if (InvoiceFragment != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);
            slidingRootNav.closeMenu();
        } else if (BedFragment != null) {
            txtTitle.setText("Home");
            fragmentUtils.replaceFragment(new HomeFragment(), Constant.HomeFragment, R.id.home_frame);
            slidingRootNav.closeMenu();
        } else {
            finish();
        }
    }

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Alerts.show(mContext, patientList.get(position).getPatientName());
            AppPreference.setStringPreference(mContext, Constant.CURRENT_PATENT_ID,
                    patientList.get(position).getPatientId());
            AppPreference.setStringPreference(mContext, Constant.CURRENT_PATENT_NAME,
                    patientList.get(position).getPatientName());
            Fragment HomeFragment = fragmentManager.findFragmentByTag(Constant.HomeFragment);
            if (HomeFragment == null) {
                onBackPressed();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void doLogout() {
        new AlertDialog.Builder(mContext)
                .setTitle("Logout")
                .setMessage("Are you sure want to doLogout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppPreference.clearAllPreferences(mContext);
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .create()
                .show();
    }

    /*private void registerBrodcast() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                if (intent.getAction().equals(Constant.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    //Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();



                }
            }
        };
        displayFirebaseRegId();

    }

    private void displayFirebaseRegId() {
        *//*String regId = AppPreference.getStringPreference(mContext, Constant.FIREBASE_TOKEN);

        Alerts.show(mContext,regId);*//*

    }*/

}
