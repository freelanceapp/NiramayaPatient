package com.ibt.niramaya.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        init();
        return rootView;
    }

    private void init() {
        imgSearch.setVisibility(View.GONE);
        imgSort.setVisibility(View.VISIBLE);
        txtTitle.setText("Add User");
        viewFlipper = rootView.findViewWithTag(R.id.vpAddUser);
        //prescriptionListApi();
    }

    @Override
    public void onClick(View v) {

    }

    /*private void showNextView() {
        Animation inAnimation = AnimationUtils.loadAnimation(mContext, R.anim.enter_from_right);
        Animation outAnimation = AnimationUtils.loadAnimation(mContext, R.anim.exit_to_left);
        viewFlipper.inAnimation(inAnimation);
        viewFlipper.outAnimation = outAnimation
        viewFlipper.setFlipInterval(500)

        viewFlipper.showNext()
    }

    private fun showPreviousView() {
        val inAnimation = AnimationUtils.loadAnimation(this, R.anim.enter_from_left)
        val outAnimation = AnimationUtils.loadAnimation(this, R.anim.exit_to_right)
        viewFlipper.inAnimation = inAnimation
        viewFlipper.outAnimation = outAnimation
        viewFlipper.setFlipInterval(500)

        viewFlipper.showPrevious()
    }*/
}
