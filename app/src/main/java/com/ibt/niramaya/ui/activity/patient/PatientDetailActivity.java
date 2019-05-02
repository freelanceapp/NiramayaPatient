package com.ibt.niramaya.ui.activity.patient;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.patient_modal.PaitentProfile;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PatientDetailActivity extends BaseActivity implements View.OnClickListener {
    private ConnectionDetector cd;
    Toolbar toolbar;
    private CircleImageView imgProfilePatient, imgEditProfilePatient;
    private TextView tvPatientName, tvPatientMobileNumber, tvPatientaadharNumber, tvPatientEmailId, tvPatientDateofBirthNumber, tvPatientGender, tvPatientbloodGroup, tvPatienthouseNo, patientSTreet, tvPatientCity, tvPatientState, tvPatientCountry, tvPatientZipCode, tvPatientGardian, tvPatientRelationship, tvPatientGardianContact, tvGardianAddress, tvPatientRelationshipStatus;
    private static final int LOAD_IMAGE_GALLERY = 123;
    private static int PICK_IMAGE_CAMERA = 124;
    private static int PERMISSION_REQUEST_CODE = 456;
    private File finalFile = null;
    private int selectedPosition = 0;
    private ArrayAdapter bloodGroupAdapter, relationshipStatusAdapter, patientGardianRelationshipAdapter;
    private String[] bloodGroupList = {"Select blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private String[] relationshipList = {"Select Relationship", "Father", "Mother", "Child", "Wife"};
    private String[] relationshipStatus = {"Select Relationship", "Yes", "No"};
    private String strBloodGroup = "", strPatientRelationship = "", strPatientRelationshipStatus = "", strRelationship = "", strGender = "", strmobile = "", strPatientImage = "";
    private EditText etPatientName, etPatientMobileNumber, etPaadharNumber, etPatientEmailId, etPatientDateofBirthNumber,
            etPatienthouseNo, etPatientSTreet, etPatientCity, etPatientState, etPatientCountry, etPatientZipCode, etPatientGardian, etPatientGardianContact, etPatientGardianAddress;
    private PaitentProfile paitentProfileData;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        final Toolbar toolbar = findViewById(R.id.toolbarPatientDetail);
        toolbar.setTitle("Patient Detail");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_product_detail);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_edit) {
                    toolbar.setTitle("Update Patient Detail");
                    ((LinearLayout) findViewById(R.id.llEditPatientDetail)).setVisibility(View.VISIBLE);
                    ((LinearLayout) findViewById(R.id.llShowPatientDetail)).setVisibility(View.GONE);

                } else {
                    ((LinearLayout) findViewById(R.id.llEditPatientDetail)).setVisibility(View.GONE);
                    ((LinearLayout) findViewById(R.id.llShowPatientDetail)).setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        if (checkPermission()) {
            Alerts.show(mContext, "Permission granted");
        } else {
            requestPermission();
        }
        findId();
        getIntentData();
        spinnerData();
        radioGroupData();
    }

    private void findId() {
        //Patient detail showing
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
        patientSTreet = findViewById(R.id.patientSTreet);
        tvPatientState = findViewById(R.id.tvPatientState);
        tvPatientCountry = findViewById(R.id.tvPatientCountry);
        tvPatientZipCode = findViewById(R.id.tvPatientZipCode);
        tvPatientGardian = findViewById(R.id.tvPatientGardian);
        tvPatientRelationship = findViewById(R.id.tvPatientRelationship);
        tvPatientGardianContact = findViewById(R.id.tvPatientGardianContact);
        tvGardianAddress = findViewById(R.id.tvGardianAddress);
        tvPatientRelationshipStatus = findViewById(R.id.tvPatientRelationshipStatus);

        //editable patient detail

        imgEditProfilePatient = findViewById(R.id.imgEditProfilePatient);
        imgEditProfilePatient.setOnClickListener(this);
        etPatientName = findViewById(R.id.etPatientName);
        etPatientMobileNumber = findViewById(R.id.etPatientMobileNumber);
        etPaadharNumber = findViewById(R.id.etPaadharNumber);
        etPatientEmailId = findViewById(R.id.etPatientEmailId);
        etPatientDateofBirthNumber = findViewById(R.id.etPatientDateofBirthNumber);
        etPatienthouseNo = findViewById(R.id.etPatienthouseNo);
        etPatientSTreet = findViewById(R.id.etPatientSTreet);
        etPatientCity = findViewById(R.id.etPatientCity);
        etPatientState = findViewById(R.id.etPatientState);
        etPatientCountry = findViewById(R.id.etPatientCountry);
        etPatientZipCode = findViewById(R.id.etPatientZipCode);
        etPatientGardian = findViewById(R.id.etPatientGardian);
        etPatientGardianContact = findViewById(R.id.etPatientGardianContact);
        etPatientGardianAddress = findViewById(R.id.etPatientGardianAddress);

        ((Button) findViewById(R.id.etUpdatePatient)).setOnClickListener(this);

        etPatientDateofBirthNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(etPatientDateofBirthNumber);
            }
        });
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
            patientSTreet.setText(paitentProfileData.getPatientStreetName());
            tvPatientState.setText(paitentProfileData.getPatientState());
            tvPatientCountry.setText(paitentProfileData.getPatientCountry());
            tvPatientZipCode.setText(paitentProfileData.getPatientZipcode());
            tvPatientGardian.setText(paitentProfileData.getPatientGardianName());
            tvPatientRelationship.setText(paitentProfileData.getPatientRelationshipWithGardian());
            tvPatientGardianContact.setText(paitentProfileData.getPatientGardianContact());
            tvGardianAddress.setText(paitentProfileData.getPatientGardianAddress());
            strPatientRelationshipStatus = paitentProfileData.getRelationshipStatus();
            if (strPatientRelationshipStatus.equals("0")) {
                strPatientRelationshipStatus = "Yes";
            } else {
                strPatientRelationshipStatus = "No";
            }
            tvPatientRelationshipStatus.setText(strPatientRelationshipStatus);
        } else {
            Alerts.show(mContext, "There is no Data");
        }

        if (paitentProfileData != null) {
            Glide.with(mContext).load(paitentProfileData.getPatientProfilePicture()).into(imgEditProfilePatient);
            etPatientName.setText(paitentProfileData.getPatientName());
            etPatientMobileNumber.setText(paitentProfileData.getPatientContact());
            etPaadharNumber.setText(paitentProfileData.getPatientAadharNumber());
            etPatientEmailId.setText(paitentProfileData.getPatientEmail());
            etPatientDateofBirthNumber.setText(paitentProfileData.getPatientDateOfBirth());

            etPatienthouseNo.setText(paitentProfileData.getPatientHouseNumber());
            etPatientCity.setText(paitentProfileData.getPatientCity());
            etPatientSTreet.setText(paitentProfileData.getPatientStreetName());
            etPatientState.setText(paitentProfileData.getPatientState());
            etPatientCountry.setText(paitentProfileData.getPatientCountry());
            etPatientZipCode.setText(paitentProfileData.getPatientZipcode());
            etPatientGardian.setText(paitentProfileData.getPatientGardianName());
            etPatientGardianContact.setText(paitentProfileData.getPatientGardianContact());
            etPatientGardianAddress.setText(paitentProfileData.getPatientGardianAddress());

        } else {
            Alerts.show(mContext, "There is no Data");
        }

    }

    private void radioGroupData() {
        strGender = paitentProfileData.getPatientGender();
        RadioGroup rg = (RadioGroup) findViewById(R.id.etPGroupGender);
        if (strGender.equals("Male")) {
            rg.check(R.id.tvMail);
        } else if (strGender.equals("Female")) {
            rg.check(R.id.tvFemail);
        } else {
            rg.check(R.id.tvOther);
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                strGender = rb.getText().toString();
            }
        });
    }

    private void spinnerData() {
        /********************************************************
         * Spinner blood group select
         * +++++++++++++++++++++++++++++++++++++++++++++++++++*/

        strBloodGroup = paitentProfileData.getPatientBloodgroup();

        Spinner spnBloodGroup = findViewById(R.id.etPspinnerBloodGroup);
        bloodGroupAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, bloodGroupList);
        spnBloodGroup.setAdapter(bloodGroupAdapter);
        int position = Arrays.asList(bloodGroupList).indexOf(strBloodGroup);
        spnBloodGroup.setSelection(position);
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
         *      Spinner GArdian Relationship
         * *****************************************************/
        strPatientRelationship = paitentProfileData.getPatientRelationshipWithGardian();
        Spinner spnPatientRelationship = findViewById(R.id.etPspinnerGardianRelationship);
        patientGardianRelationshipAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, relationshipList);
        spnPatientRelationship.setAdapter(patientGardianRelationshipAdapter);
        int position1 = Arrays.asList(relationshipList).indexOf(strPatientRelationship);
        spnPatientRelationship.setSelection(position1);
        spnPatientRelationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strPatientRelationship = relationshipList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        patientGardianRelationshipAdapter.notifyDataSetChanged();
        /*******************************************************
         *      Spinner Relationship status
         * *****************************************************/
        strPatientRelationshipStatus = paitentProfileData.getRelationshipStatus();
        Spinner spnRelationshipstatus = findViewById(R.id.etPspinnerRelationship);
        relationshipStatusAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, relationshipStatus);
        spnRelationshipstatus.setAdapter(relationshipStatusAdapter);
        if (strPatientRelationshipStatus.equals("1")) {
            spnRelationshipstatus.setSelection(1);
        } else if (spnRelationshipstatus.equals("0")) {
            spnRelationshipstatus.setSelection(2);
        } else {
            spnRelationshipstatus.setSelection(0);
        }
        spnRelationshipstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strPatientRelationshipStatus = relationshipList[position];
                if (strPatientRelationshipStatus.equals("Yes")) {
                    strPatientRelationshipStatus = "1";
                } else if (strPatientRelationshipStatus.equals("No")) {
                    strPatientRelationshipStatus = "0";
                } else {
                    strPatientRelationshipStatus = "";
                }
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

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Alerts.show(mContext, "Permission not granted");
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (mContext.getContentResolver() != null) {
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgEditProfilePatient.setImageBitmap(photo);
                Uri tempUri = getImageUri(mContext, photo);
                finalFile = new File(getRealPathFromURI(tempUri));

                //api hit

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == LOAD_IMAGE_GALLERY && resultCode == RESULT_OK && null != data) {
            final Uri uriImage = data.getData();
            final InputStream inputStream;
            try {
                inputStream = mContext.getContentResolver().openInputStream(uriImage);
                final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                imgEditProfilePatient.setImageBitmap(imageMap);

                String imagePath2 = getPath(uriImage);
                File imageFile = new File(imagePath2);

                finalFile = imageFile;
                strPatientImage = convertToBase64(finalFile.getAbsolutePath());

                //api hit
            } catch (FileNotFoundException e) {
                Toast.makeText(mContext, "Image not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } else {

        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String strPath = cursor.getString(column_index);
        cursor.close();
        return strPath;
    }

    private String convertToBase64(String path) {

        Bitmap bm = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    private void selectImage() {
        try {
            PackageManager pm = mContext.getPackageManager();
            int permission = pm.checkPermission(Manifest.permission.CAMERA, mContext.getPackageName());
            if (permission == PackageManager.PERMISSION_GRANTED) {
                final CharSequence[] choose = {"Pick From Camera", "Choose From Gallery", "Cancel"};
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("Select Option");
                builder.setItems(choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choose[which].equals("Pick From Camera")) {
                            dialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, PICK_IMAGE_CAMERA);
                        } else if (choose[which].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent i = new Intent(
                                    Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, LOAD_IMAGE_GALLERY);
                        } else if (choose[which].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            } else
                Toast.makeText(mContext, "Camera Permission error", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(mContext, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgEditProfilePatient:
                try {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, LOAD_IMAGE_GALLERY);
                    } else {
                        selectImage();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.etUpdatePatient:
                updatePatientProfileApi();
                break;
        }
    }

    private void updatePatientProfileApi() {
        if (cd.isNetworkAvailable()) {
            String strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID);
            String strPatientId = AppPreference.getStringPreference(mContext, Constant.PATIENT_ID);
            String strName = etPatientName.getText().toString();
            strmobile = etPatientMobileNumber.getText().toString();
            String strAadahr = etPaadharNumber.getText().toString();
            String strEmailadd = etPatientEmailId.getText().toString();
            String strDob = etPatientDateofBirthNumber.getText().toString();
            String strHouseNo = etPatienthouseNo.getText().toString();
            String strStreet = etPatientSTreet.getText().toString();
            String strCity = etPatientCity.getText().toString();
            String strState = etPatientState.getText().toString();
            String strCountry = etPatientCountry.getText().toString();
            String strZipcode = etPatientZipCode.getText().toString();
            String strGardian = etPatientGardian.getText().toString();
            String strGardianContact = etPatientGardianContact.getText().toString();
            String strGarAddress = etPatientGardianAddress.getText().toString();

            if (strName.isEmpty()) {
                Alerts.show(mContext, "Patient name should not be empty!!!");
            } else if (strAadahr.isEmpty()) {
                Alerts.show(mContext, "Aadhar number should not be empty!!!");
            } else if (strmobile.isEmpty()) {
                Alerts.show(mContext, "Mobile number should not be empty!!!");
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
            } else {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.updatePatientProfie(strName, strBloodGroup, strmobile, strDob, strEmailadd,
                        strHouseNo, strStreet, strCity, strState, strCountry, strZipcode, strGender, strGardian, strPatientRelationship,
                        strGardianContact, strGarAddress, strAadahr, strUserId, strPatientRelationshipStatus, strPatientId, strPatientImage), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (!jsonObject.getBoolean("error")) {
                                onBackPressed();
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
