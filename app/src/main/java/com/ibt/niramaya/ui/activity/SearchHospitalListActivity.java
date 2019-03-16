package com.ibt.niramaya.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.SearchHospitalListAdapter;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchHospitalListActivity extends BaseActivity {
    RecyclerView rvSearchHospital;
    private SearchHospitalListAdapter searchHospitalListAdapter;
    private List<String> hospitaList = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hospital_list);

        rvSearchHospital = findViewById(R.id.rvSearchHospital);

        for (int i = 0; i<= 10; i++){
        hospitaList.add("Apollo Hospital");
        hospitaList.add("Palasia square new police station Indore");
        hospitaList.add("3.5");
        hospitaList.add("1 km");
        }


        rvSearchHospital.setHasFixedSize(true);
        rvSearchHospital.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        searchHospitalListAdapter = new SearchHospitalListAdapter(mContext,hospitaList);
        rvSearchHospital.setAdapter(searchHospitalListAdapter);
        searchHospitalListAdapter.notifyDataSetChanged();

    }
}
