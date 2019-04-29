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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AddNewPatientActivity extends BaseActivity implements View.OnClickListener {
    private ConnectionDetector cd;
    private static final int LOAD_IMAGE_GALLERY = 123;
    private static int PICK_IMAGE_CAMERA = 124;
    private static int PERMISSION_REQUEST_CODE = 456;
    private File finalFile = null;
    private int selectedPosition = 0;
    private ArrayAdapter bloodGroupAdapter, relationshipStatusAdapter, patientGardianRelationshipAdapter;
    private String[] bloodGroupList = {"Select blood group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    private String[] relationshipList = {"Select Relationship", "Father", "Mother", "Child", "Wife"};
    private String strBloodGroup = "", strPatientRelationship = "", strRelationship = "", strGender = "", strmobile = "";
    private EditText patientName, aadharNumber, patientEmailId, patientDateofBirthNumber, patienthouseNo,
            patientSTreet, patientCity, patientState, patientCountry, patientZipCode, patientGardian, patientGardianContact, patientGardianAddress;
    private TextView patientMobileNumber;
    private CircleImageView imgProfilePatient;
    private String strMedicineImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbarAddnewPatient);
        toolbar.setTitle("Add Patient");
        setTitleColor(R.color.white);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findId();
        spinnerData();
        radioGroupData();
        onAddGardianRelation();
        if (checkPermission()) {
            Alerts.show(mContext, "Permission granted");
        } else {
            requestPermission();
        }
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
        imgProfilePatient.setOnClickListener(this);
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
         *      Spinner GArdian Relationship
         * *****************************************************/
        Spinner spnPatientRelationship = findViewById(R.id.spinnerGardianRelationship);
        patientGardianRelationshipAdapter = new ArrayAdapter(mContext, R.layout.row_spn_normal, relationshipList);
        spnPatientRelationship.setAdapter(patientGardianRelationshipAdapter);
        spnPatientRelationship.setSelection(selectedPosition);
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
                imgProfilePatient.setImageBitmap(photo);
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
                imgProfilePatient.setImageBitmap(imageMap);

                String imagePath2 = getPath(uriImage);
                File imageFile = new File(imagePath2);

                finalFile = imageFile;
                strMedicineImage = convertToBase64(finalFile.getAbsolutePath());

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
            case R.id.ragisterPatient:
                createPatientRagisterApi();
                break;
            case R.id.imgProfilePatient:
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
        }
    }

    private void onAddGardianRelation() {
        ((EditText) findViewById(R.id.patientGardian)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((LinearLayout) findViewById(R.id.llGardianDetail)).setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String etTExt = patientGardian.getText().toString();
                if (etTExt.isEmpty()) {
                    ((LinearLayout) findViewById(R.id.llGardianDetail)).setVisibility(View.GONE);
                }

            }
        });
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
            } /*else if (strGardian.isEmpty()) {
                Alerts.show(mContext, "Gardian namem should not be empty!!!");
            } else if (strGardianContact.isEmpty()) {
                Alerts.show(mContext, "Gardian name should not be empty!!!");
*//*
                strName,strBloodGroup,
                        strmobile,strDob,strEmailadd,strHouseNo,strStreet,strCity,strState,strCountry,strZipcode,strGender,
                        strGardian,strRelation,strGardianContact,strGarAddress,strAadahr,"",strRelationship,""*//*
            }*/ else {
                RetrofitService.getServerResponse(new Dialog(mContext), retrofitApiClient.createPatientProfie(strName, strBloodGroup,
                        strmobile, strDob, strEmailadd, strHouseNo, strStreet, strCity, strState, strCountry, strZipcode, strGender,
                        strGardian, strPatientRelationship, strGardianContact, strGarAddress, strAadahr, strUserId, strRelationship, ""), new WebResponse() {
                    @Override
                    public void onResponseSuccess(Response<?> result) {
                        ResponseBody responseBody = (ResponseBody) result.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responseBody.string());
                            if (jsonObject.getBoolean("error")) {
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

