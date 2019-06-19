package com.ibt.niramaya.ui.activity.ambulance;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.ambulance.DriverLocation;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //initRetrofit();
        //init();

        cvDriverDetail = findViewById(R.id.cvDriverDetail);
        tvDriverName = findViewById(R.id.tvDriverName);
        tvDriverDistance = findViewById(R.id.tvDriverDistance);
        txtTitle = findViewById(R.id.txtTitle);

        (findViewById(R.id.btnClose)).setOnClickListener(v -> {
            startActivity(new Intent(mContext, AmbulanceDetailActivity.class)
            .putExtra("DRIVER_ID", clickedDriverId));
        });

        initFirebaseDatabase();
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
                    if (driverList.get(i).isDriverStatus()) {
                        createMarker(driverList.get(i).getDriverLat(), driverList.get(i).getDriverLong(),
                                driverList.get(i).getDriverName(), driverList.get(i).getDriverId());
                        activeDriverList.add(driverList.get(i));
                    }
                }

                /*bound camera between these lat lng*/
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (DriverLocation dLoc : driverList) {
                    builder.include(new LatLng(dLoc.getDriverLat(), dLoc.getDriverLong()));
                }
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

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

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

    /***********************************************************
     * Move Ambulance Smoothly
     ************************************************************/
    private double bearingBetweenLocations(LatLng latLng1,LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return brng;
    }

    private void rotateMarker(final Marker marker, final float toRotation) {
        if(!isMarkerRotating) {
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final float startRotation = marker.getRotation();
            final long duration = 1000;

            final Interpolator interpolator = new LinearInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    isMarkerRotating = true;

                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = interpolator.getInterpolation((float) elapsed / duration);

                    float rot = t * toRotation + (1 - t) * startRotation;

                    marker.setRotation(-rot > 180 ? rot / 2 : rot);
                    if (t < 1.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    } else {
                        isMarkerRotating = false;
                    }
                }
            });
        }
    }

    private void animateMarkerNew(final LatLng startPosition, final LatLng destination, final Marker marker) {

        if (marker != null) {

            final LatLng endPosition = new LatLng(destination.latitude, destination.longitude);

            final float startRotation = marker.getRotation();
            final LatLngInterpolatorNew latLngInterpolator = new LatLngInterpolatorNew.LinearFixed();

            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(2000); // duration 3 second
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(animation -> {
                try {
                    float v = animation.getAnimatedFraction();
                    LatLng newPosition = latLngInterpolator.interpolate(v, startPosition, endPosition);
                    marker.setPosition(newPosition);
                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                            .target(newPosition)
                            .zoom(18f)
                            .build()));

                    marker.setRotation(getBearing(startPosition, new LatLng(destination.latitude, destination.longitude)));
                } catch (Exception ex) {
                    //I don't care atm..
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    // if (mMarker != null) {
                    // mMarker.remove();
                    // }
                    // mMarker = googleMap.addMarker(new MarkerOptions().position(endPosition).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_car)));
//https://stackoverflow.com/questions/46422397/how-to-display-smooth-movement-of-current-location-in-google-map-android
                }
            });
            valueAnimator.start();
        }
    }

    private interface LatLngInterpolatorNew {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class LinearFixed implements LatLngInterpolatorNew {
            @Override
            public LatLng interpolate(float fraction, LatLng a, LatLng b) {
                double lat = (b.latitude - a.latitude) * fraction + a.latitude;
                double lngDelta = b.longitude - a.longitude;
                // Take the shortest path across the 180th meridian.
                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * fraction + a.longitude;
                return new LatLng(lat, lng);
            }
        }
    }

    private float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
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
                double distance = calCulateDistance(clickedDriver.getDriverLat(), clickedDriver.getDriverLong());
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
    private double calCulateDistance(double dLat, double dLong){
        Location locationA = new Location("point A");
        locationA.setLatitude(latitude);
        locationA.setLongitude(longitude);
        Location locationB = new Location("point B");
        locationB.setLatitude(dLat);
        locationB.setLongitude(dLong);

        double distance = (locationA.distanceTo(locationB))/1000;
        return distance;
    }
}