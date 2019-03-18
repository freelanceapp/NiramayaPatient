package com.ibt.niramaya.ui.fragment.blood_donation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.CheckAvailabilityAdapter;
import com.ibt.niramaya.adapter.InvoiceListAdapter;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;
import static com.ibt.niramaya.ui.activity.HomeActivity.txtTitle;

public class CheckAvailbilityFragment extends BaseFragment implements View.OnClickListener {

    private List<String> chekcBloodAvailiblityList = new ArrayList<>();
    private View rootView;
    private CheckAvailabilityAdapter checkAvailabilityAdapter;
    private RelativeLayout rlSearchCheckAvailability;
    private EditText etSearchBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_check_availability, container, false);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();
        rlSearchCheckAvailability = rootView.findViewById(R.id.rl_search_checkAvailability);
        etSearchBox = rootView.findViewById(R.id.et_search_checkAvailability);
        chekcAvailiblity();
    }

    private void chekcAvailiblity() {
        for (int i = 0; i < 2; i++) {
            chekcBloodAvailiblityList.add("01");
            chekcBloodAvailiblityList.add("Apollo");
            chekcBloodAvailiblityList.add("A+");
            chekcBloodAvailiblityList.add("50");
        }
        RecyclerView rvSearchAvailability = rootView.findViewById(R.id.rvSearchCheckAvailability);
        rvSearchAvailability.setHasFixedSize(true);
        rvSearchAvailability.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        checkAvailabilityAdapter = new CheckAvailabilityAdapter(mContext, chekcBloodAvailiblityList);
        rvSearchAvailability.setAdapter(checkAvailabilityAdapter);
        checkAvailabilityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
