package com.ibt.niramaya.ui.fragment.home_detail_fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseFragment;

public class FacilitiesFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_facilities, container, false);

        initViews();

        return rootView;
    }

    private void initViews() {

        String facilities = getArguments().getString("FACILITIES");

        TextView txtFacilities = rootView.findViewById(R.id.txtFacilities);
        if (!facilities.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txtFacilities.setText(Html.fromHtml(facilities, Html.FROM_HTML_MODE_COMPACT));
            } else {
                txtFacilities.setText(Html.fromHtml(facilities));
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
