package com.ibt.niramaya.ui.fragment;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.DumHospitalListAdapter;
import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.HospitalCategoryAdapter;
import com.ibt.niramaya.adapter.HospitalListAdapter;
import com.ibt.niramaya.adapter.ViewPagerAdapter;
import com.ibt.niramaya.constant.Constant;
import com.ibt.niramaya.modal.hospital.HospitalDatum;
import com.ibt.niramaya.modal.hospital.HospitalListModel;
import com.ibt.niramaya.retrofit.RetrofitService;
import com.ibt.niramaya.retrofit.WebResponse;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.AppPreference;
import com.ibt.niramaya.utils.AppProgressDialog;
import com.ibt.niramaya.utils.BaseFragment;
import com.ibt.niramaya.utils.ConnectionDetector;
import com.ibt.niramaya.utils.GpsTracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;
import static com.ibt.niramaya.ui.activity.HomeActivity.txtTitle;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private Handler imageHandler;
    private Runnable imageRunnable;
    private ViewPager pagerSuccess;
    private ViewPagerAdapter adapter;
    private Double currentLat, currentLong;
    private List<Integer> successImagesList = new ArrayList<>();

    private GpsTracker gpsTracker;
    private TextView txtLocationAddress;

    private HospitalListAdapter hospitalListAdapter;
    private DumHospitalListAdapter popularHospitalAdapter;
    private List<HospitalDatum> hospitalList = new ArrayList<>();
    private List<String> popularHospitalList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        retrofitApiClient = RetrofitService.getRetrofit();
        gpsTracker = new GpsTracker(mContext);
        currentLat = gpsTracker.getLatitude();
        currentLong = gpsTracker.getLongitude();
        init();
        return rootView;
    }

    private void init() {

        imgSearch.setVisibility(View.VISIBLE);
        imgSort.setVisibility(View.GONE);

        activity = getActivity();
        mContext = getActivity();
        cd = new ConnectionDetector(mContext);
        initViewPager();
        nearByHospitalList();
        popularHospitalList();
        hospitalCategoryList();

        /*Alerts.show(mContext, "Latitude : "+gpsTracker.getLatitude()+"Longitude : "+gpsTracker.getLongitude());
        Log.v("CURRENT_LOCATION", "Latitude : "+gpsTracker.getLatitude()+", Longitude : "+gpsTracker.getLongitude());


        myCurrentAddress(currentLat, currentLong);*/
    }

    private void initViewPager() {
        pagerSuccess = rootView.findViewById(R.id.viewPager);
        txtLocationAddress = rootView.findViewById(R.id.txtLocationAddress);
        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pagerSuccess, true);

        txtLocationAddress.setText(myCurrentAddress(currentLat, currentLong));

        successImagesList.clear();
        for (int i = 0; i < 6; i++) {
            successImagesList.add(R.drawable.default_img_pager);
        }

        adapter = new ViewPagerAdapter(mContext, successImagesList, this);
        pagerSuccess.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        imageHandler = new Handler();
        imageRunnable = new Runnable() {
            @Override
            public void run() {
                pagerSlide();
            }
        };
        imageHandler.postDelayed(imageRunnable, 3000);
    }

    public void pagerSlide() {
        if (pagerSuccess == null)
            return;
        int successPos = pagerSuccess.getCurrentItem();
        successPos++;
        if (successPos != successImagesList.size()) {
            pagerSuccess.setCurrentItem(successPos);
            imageHandler.postDelayed(imageRunnable, 3000);
        } else {
            pagerSuccess.setCurrentItem(0);
            imageHandler.postDelayed(imageRunnable, 3000);
        }
    }

    /**********************************************/
    /*
     * Hospital list
     * */
    private void nearByHospitalList() {

        if (currentLat.equals(0.0)) {
                currentLat = gpsTracker.getLatitude();
                currentLong = gpsTracker.getLongitude();
        }

        String userID = AppPreference.getStringPreference(mContext, Constant.USER_ID);

        if (cd.isNetworkAvailable()){
            RetrofitService.getHospitalList(new Dialog(mContext), retrofitApiClient.hospitalList(
                    "1", "", String.valueOf(currentLat), String.valueOf(currentLong), "1"), new WebResponse() {
                        @Override
                        public void onResponseSuccess(Response<?> result) {
                            HospitalListModel hospitalListModel = (HospitalListModel) result.body();
                            if (!hospitalListModel.getError() && hospitalListModel.getHospitalData().size()>0){
                                hospitalList = hospitalListModel.getHospitalData();
                                RecyclerView recyclerViewNear = rootView.findViewById(R.id.recyclerViewNear);
                                recyclerViewNear.setHasFixedSize(true);
                                recyclerViewNear.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                                hospitalListAdapter = new HospitalListAdapter(hospitalList, mContext, HomeFragment.this);
                                recyclerViewNear.setAdapter(hospitalListAdapter);
                            }
                        }

                        @Override
                        public void onResponseFailed(String error) {

                        }
                    });
        }

        //₹

        /*for (int i = 0; i < 10; i++) {
            hospitalList.add("Name");
        }*/
        /*RecyclerView recyclerViewNear = rootView.findViewById(R.id.recyclerViewNear);
        recyclerViewNear.setHasFixedSize(true);
        recyclerViewNear.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        hospitalListAdapter = new DumHospitalListAdapter(popularHospitalList, mContext, this);
        recyclerViewNear.setAdapter(hospitalListAdapter);*/
    }

    /*
     * Hospital list
     * */
    private void popularHospitalList() {
        for (int i = 0; i < 10; i++) {
            popularHospitalList.add("Name");
        }
        RecyclerView recyclerViewPopularity = rootView.findViewById(R.id.recyclerViewPopularity);
        recyclerViewPopularity.setHasFixedSize(true);
        recyclerViewPopularity.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        popularHospitalAdapter = new DumHospitalListAdapter(popularHospitalList, mContext, this);
        recyclerViewPopularity.setAdapter(popularHospitalAdapter);
        popularHospitalAdapter.notifyDataSetChanged();
    }

    /*
     * Hospital Category list
     * */
    private void hospitalCategoryList() {
        for (int i = 0; i < 10; i++) {
            popularHospitalList.add("Name");
        }
        RecyclerView recyclerViewCategory = rootView.findViewById(R.id.recyclerViewCategory);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(getActivity(),
                2, GridLayoutManager.HORIZONTAL, false));
        HospitalCategoryAdapter popularHospitalAdapter = new HospitalCategoryAdapter(popularHospitalList, mContext, this);
        recyclerViewCategory.setAdapter(popularHospitalAdapter);
        popularHospitalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    /*private void getLatLong() {
        GpsTracker gpsTracker = new GpsTracker(ctx);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        getAddressList();
    }*/

    private String myCurrentAddress(Double latitude, Double longitude){

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());

        String address = "";

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            Alerts.show(mContext, "Address : "+address+"\nCity : "+city+"\nState : "+state+"\nCountry : "+country+
                    "\nPostal Code : "+postalCode+"\nknown Name : "+knownName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

}
