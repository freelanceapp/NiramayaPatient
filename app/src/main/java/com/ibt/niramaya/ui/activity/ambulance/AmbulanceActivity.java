package com.ibt.niramaya.ui.activity.ambulance;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.ambulance.DriverLocation;
import com.ibt.niramaya.modal.ambulance.driver_detail.Ambulance;
import com.ibt.niramaya.modal.ambulance.driver_detail.AmbulanceDetailModel;
import com.ibt.niramaya.modal.driver.driver_list_modal.DriverList;
import com.ibt.niramaya.modal.driver.driver_list_modal.DriverMainModal;
import com.ibt.niramaya.retrofit.RetrofitApiClient;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.GpsTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

public class AmbulanceActivity extends BaseActivity implements LocationListener,
        OnMapReadyCallback, RoutingListener, GoogleMap.OnMarkerClickListener {

    private RetrofitApiClient client;
    private DriverMainModal mainModal;
    private List<DriverList> driverListsUpdate = new ArrayList<>();
    private GoogleMap mMap;
    private static final String TAG = "TAG";
    private boolean isUp = false;

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
    private ArrayList<DriverLocation> driverList;
    private ArrayList<DriverLocation> activeDriverList;
    private List<Polyline> polylines = new ArrayList<>();
    private static final int[] COLORS = new int[]{R.color.colorAccent};
    private Marker driverMarker;
    private boolean isMarkerRotating =false;
    private RelativeLayout cvDriverDetail;
    private String clickedDriverId;
    private TextView tvDriverName, tvDriverDistance, txtTitle;
    private ImageView ivCurrentLocation;
    private Button btnMore;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout layoutBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //initRetrofit();
        init();

        initFirebaseDatabase();
    }

    private void init() {
        cvDriverDetail = findViewById(R.id.cvDriverDetail);
        tvDriverName = findViewById(R.id.tvDriverName);
        tvDriverDistance = findViewById(R.id.tvDriverDistance);
        txtTitle = findViewById(R.id.txtTitle);
        ivCurrentLocation = findViewById(R.id.ivCurrentLocation);
        btnMore = findViewById(R.id.btnClose);

        ivCurrentLocation.setOnClickListener(v -> getLatLong());

        btnMore.setOnClickListener(v -> {
            /*startActivity(new Intent(mContext, AmbulanceDetailActivity.class)
                    .putExtra("DRIVER_ID", clickedDriverId));*/
            fetchAmbulanceDetail();
        });

        Places.initialize(getApplicationContext(), getResources().getString(R.string.google_key));
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                latLng = place.getLatLng();
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                Log.e("lat : ", String.valueOf(latitude));
                Log.e("longi : ", String.valueOf(longitude));
                initFirebaseDatabase();
            }

            @Override
            public void onError(Status status) {
                Alerts.show(mContext, status.getStatusMessage());
            }
        });



    }

    private void initFirebaseDatabase(){
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("driver");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driverList = new ArrayList<>();
                activeDriverList = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    DriverLocation driverLocation = postSnapshot.getValue(DriverLocation.class);
                    driverList.add(driverLocation);
                }
                if (mMap != null){
                    mMap.clear();
                }

                for(int i = 0 ; i < driverList.size() ; i++) {
                    if (driverList.get(i).isDriverStatus() &&
                            calculateDistance(driverList.get(i).getDriverLat(),
                                    driverList.get(i).getDriverLong())<100.00) {
                        createMarker(driverList.get(i).getDriverLat(), driverList.get(i).getDriverLong(),
                                driverList.get(i).getDriverName(), driverList.get(i).getDriverId());
                        activeDriverList.add(driverList.get(i));
                    }
                }

                addMarker(latLng);

                /*bound camera between these lat lng*/
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (DriverLocation dLoc : activeDriverList) {
                    builder.include(new LatLng(dLoc.getDriverLat(), dLoc.getDriverLong()));
                }
                builder.include(new LatLng(latitude, longitude));
                LatLngBounds bounds = builder.build();
                int padding = 100; // offset from edges of the map in pixels
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cu);
                mMap.moveCamera(cu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getLatLong() {
        GpsTracker gpsTracker = new GpsTracker(this);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        latLng = new LatLng(latitude, longitude);
        initFirebaseDatabase();
    }

    private void getAddressList(double aLat, double aLong) {
        // AppProgressDialog.show(dialog);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(aLat, aLong, 1);
            if (addresses.size() > 0) {
                //  AppProgressDialog.hide(dialog);
            } else {
                // AppProgressDialog.show(dialog);
                new Handler().postDelayed(() -> getLatLong(), 3000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());

       // getDriverListDataApi();

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
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mMap.animateCamera(cameraUpdate);
            addMarker(latLng);
        }
/*
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);*/

        LatLng indore = new LatLng(22.719568, 75.857727);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        addMarker(indore);

        mMap.setOnInfoWindowClickListener(arg0 -> {
            //DriverLocation dl = driverList.get(Integer.parseInt(arg0.getSnippet()));
            Alerts.show(mContext, arg0.getTitle()+" :=: "+arg0.getId()+" :=: ");
        });
        mMap.setOnMarkerClickListener(this);

    }

    private void addMarker(LatLng location) {
        if (mMap != null) {
            MarkerOptions mMarkerOption = new MarkerOptions();
            mMarkerOption.position(location);
            //mMarkerOption.title("My location");
            mMarkerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_placeholder));
            removeMarker();
            mMarker = mMap.addMarker(mMarkerOption);

            /*driverList.add(new DriverLocation("1", "Driver 1", 22.72591965, 75.88166714));
            driverList.add(new DriverLocation("1", "Driver 2", 22.72370295, 75.88887691));
            driverList.add(new DriverLocation("1", "Driver 3", 22.7228321, 75.87831974));
            driverList.add(new DriverLocation("1", "Driver 4", 22.7301946, 75.88527203));
            driverList.add(new DriverLocation("2", "Driver 5", 22.71507333, 75.88278294));*/

            /*for(int i = 0 ; i < driverList.size() ; i++) {
                createMarker(driverList.get(i).getDriverLat(), driverList.get(i).getDriverLong(),
                        driverList.get(i).getDriverName(), driverList.get(i).getDriverName());
            }

            createRoute();*/

        }
    }

    private void removeMarker() {
        if (mMap != null && mMarker != null) {
            mMarker.remove();
        }
    }

    /*************************************************************
     *
     ************************************************************/
    protected Marker createMarker(double latitude, double longitude, String title, String snippet) {

        MarkerOptions dMarkerOption = new MarkerOptions();
        dMarkerOption.position(new LatLng(latitude, longitude));
        dMarkerOption.anchor(0.5f,0.5f);
        dMarkerOption.title(title);
        dMarkerOption.snippet(snippet);
        dMarkerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.ambulance));

        return mMap.addMarker(dMarkerOption);
    }

    private void createRoute(){
        Routing routing = new Routing.Builder()
                .key("AIzaSyBvaYGedz5oMgLpYMF42wtJE8VIT28juM8")//NonRestrictedKey
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(false)
                .waypoints(new LatLng(driverList.get(0).getDriverLat(), driverList.get(0).getDriverLong()),
                        new LatLng(driverList.get(1).getDriverLat(), driverList.get(0).getDriverLong()))
                .build();
        routing.execute();
    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int p1) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(driverList.get(0).getDriverLat(), driverList.get(0).getDriverLong()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(driverList.get(0).getDriverLat(), driverList.get(0).getDriverLong()), 15);
        //mMap.animateCamera(cameraUpdate);
        mMap.moveCamera(cameraUpdate);




        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }

        // Start marker
        /*MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(22.7301946, 75.88527203));
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_placeholder));
        mMap.addMarker(options);*/

        // End marker
        /*options = new MarkerOptions();
        options.position(new LatLng(22.71507333, 75.88278294));
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.icf_hospital_location_pin));
        mMap.addMarker(options);*/
    }

    @Override
    public void onRoutingCancelled() {

    }

    /****************************************************************
     * Marker Clicked Event
     *****************************************************************/
    @Override
    public boolean onMarkerClick(Marker marker) {
        clickedDriverId = marker.getSnippet();
        for(DriverLocation clickedDriver : activeDriverList){
            if (clickedDriver.getDriverId().equals(clickedDriverId)){
                cvDriverDetail.setVisibility(View.VISIBLE);
                double distance = calculateDistance(clickedDriver.getDriverLat(), clickedDriver.getDriverLong());
                String strDistance = String.format("%.2f", distance);
                /*Alerts.show(mContext, "Marker Clicked : "+marker.getTitle()
                        +"\nDriver Id : "+clickedDriverId
                        +"\nDriver Distance : "+strDistance+"KM");*/
                tvDriverName.setText("Distance : "+strDistance+"km");
                tvDriverDistance.setText("Driver Name : "+marker.getTitle());
            }
        }
        return true;
    }

    /********************************************************************
     *
     **********************************************************************/
    private double calculateDistance(double dLat, double dLong){
        Location locationA = new Location("point A");
        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(dLat);
        locationB.setLongitude(dLong);

        double distance = (locationA.distanceTo(locationB))/1000;
        return distance;
    }

    /**/
    private void fetchAmbulanceDetail() {
        if (cd.isNetworkAvailable()) {
            RetrofitService.ambulanceDetail(new Dialog(mContext), retrofitApiClient.ambulanceDetail(clickedDriverId), new WebResponse() {
                @Override
                public void onResponseSuccess(Response<?> result) {
                    AmbulanceDetailModel ambulanceModel = (AmbulanceDetailModel) result.body();
                    if (!ambulanceModel.getError()){
                        Alerts.show(mContext, ambulanceModel.getMessage());
                        openBottomSheet(ambulanceModel.getAmbulance());
                    }
                }

                @Override
                public void onResponseFailed(String error) {

                }
            });
        }
    }

    private void openBottomSheet(Ambulance ambulance) {

        AlertDialog.Builder dialogBox = new AlertDialog.Builder(mContext);
        dialogBox.setCancelable(false);

        LayoutInflater li = LayoutInflater.from(mContext);
        final View dialogView = li.inflate(R.layout.dialog_ambulance_detail, null);
        dialogBox.setView(dialogView);
        final AlertDialog alertDialog = dialogBox.create();
        alertDialog.show();

        RecyclerView rvAmbulance = dialogView.findViewById(R.id.rvAmbulance);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnBook = dialogView.findViewById(R.id.btnBook);

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());
        btnBook.setOnClickListener(v -> Alerts.show(mContext, "In Process..."));



    }
}
