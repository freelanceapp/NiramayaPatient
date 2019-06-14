package com.ibt.niramaya.ui.activity.ambulance;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibt.niramaya.BuildConfig;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.driver.driver_list_modal.DriverList;
import com.ibt.niramaya.modal.driver.driver_list_modal.DriverMainModal;
import com.ibt.niramaya.retrofit.RetrofitApiClient;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.GpsTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class AmbulanceActivity extends BaseActivity implements LocationListener, OnMapReadyCallback {

    private RetrofitApiClient client;
    private DriverMainModal mainModal;
    private List<DriverList> driverListsUpdate = new ArrayList<>();
    private GoogleMap mMap;
    private static final String TAG = "TAG";
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private double latitude = 0.0;
    private double longitude = 0.0;
    private long UPDATE_INTERVAL = 2000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */
    private LocationRequest mLocationRequest;
    private static final int DEFAULT_ZOOM_LEVEL = 14;
    private LatLng latLng;
    private Marker mMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        initRetrofit();
        init();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor mHttpLoginInterceptor = new HttpLoggingInterceptor();

        mHttpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder mOkClient = new OkHttpClient.Builder().readTimeout(300,
                TimeUnit.SECONDS).writeTimeout(300, TimeUnit.SECONDS).connectTimeout(300, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            mOkClient.addInterceptor(mHttpLoginInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://niramaya-hospital.firebaseio.com/")
                .client(mOkClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(RetrofitApiClient.class);
        getDriverListDataApi();
    }

    private void getDriverListDataApi() {
        Call<DriverMainModal> call = client.driverList();
        call.enqueue(new Callback<DriverMainModal>() {
            @Override
            public void onResponse(Call<DriverMainModal> call, Response<DriverMainModal> response) {
                mainModal = response.body();
                driverListsUpdate.clear();
                if (mainModal.getDriverData() != null) {
                    driverListsUpdate.addAll(mainModal.getDriverData().getDriverList());
                } else {
                    Alerts.show(mContext, "No driver found...!!!");
                }
            }

            @Override
            public void onFailure(Call<DriverMainModal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("driver");
        mFirebaseInstance.getReference("driver").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DriverModel driverModel = dataSnapshot.getValue(DriverModel.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });
        startLocationUpdates();
        getLatLong();
    }

    protected void startLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        onLocationChanged(locationResult.getLastLocation());
                    }
                },
                Looper.myLooper());
    }

    private void getLatLong() {
        GpsTracker gpsTracker = new GpsTracker(this);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        latLng = new LatLng(latitude, longitude);
        getAddressList();
    }

    private void getAddressList() {
        // AppProgressDialog.show(dialog);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                //  AppProgressDialog.hide(dialog);
            } else {
                // AppProgressDialog.show(dialog);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getLatLong();
                    }
                }, 3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

        getDriverListDataApi();

        if (latitude > 0) {
            addMarker(latLng);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Alerts.show(this, "Please enable location permission...!!!");
            return;
        }

        getLatLong();
        if (latLng != null) {
            latitude = latLng.latitude;
            longitude = latLng.longitude;
        }
        if (latitude > 0) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 22);
            mMap.animateCamera(cameraUpdate);
            addMarker(latLng);
        }
    }

    private void addMarker(LatLng location) {
        if (mMap != null) {
            MarkerOptions mMarkerOption = new MarkerOptions();
            mMarkerOption.position(location);
            mMarkerOption.title("My location");
            mMarkerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_current));
            removeMarker();
            mMarker = mMap.addMarker(mMarkerOption);
        }
    }

    private void removeMarker() {
        if (mMap != null && mMarker != null) {
            mMarker.remove();
        }
    }

}
