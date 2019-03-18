package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Switch;
import android.widget.ViewFlipper;

import com.ibt.niramaya.R;
import com.ibt.niramaya.adapter.InvoiceListAdapter;
import com.ibt.niramaya.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;
import static com.ibt.niramaya.ui.activity.HomeActivity.txtTitle;

public class AddUserFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_user, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        txtTitle.setText("Add User");
        viewFlipper = view.findViewById(R.id.vpAddUser);
        (view.findViewById(R.id.ivAddNewUser)).setOnClickListener(this);
        (view.findViewById(R.id.btnNext)).setOnClickListener(this);
        //prescriptionListApi();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ivAddNewUser :
                viewFlipper.showNext();
                break;
            case R.id.btnNext :
                viewFlipper.showPrevious();
                break;
        }
    }

}
