package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseFragment;

import static com.ibt.niramaya.ui.activity.HomeActivity.imgSearch;
import static com.ibt.niramaya.ui.activity.HomeActivity.imgSort;

public class DocumentsFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    private ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_documents, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

    }

    private void init(View view) {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {

    }

}
