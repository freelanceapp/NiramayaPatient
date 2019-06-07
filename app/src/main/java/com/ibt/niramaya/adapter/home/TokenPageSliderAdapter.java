package com.ibt.niramaya.adapter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.token.TokenDatum;

import java.util.ArrayList;

public class TokenPageSliderAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<TokenDatum> sliderList;
    private View.OnClickListener clickLisner;

    public TokenPageSliderAdapter(Context context, ArrayList<TokenDatum> sliderList, View.OnClickListener clickListener) {
        this.context = context;
        this.sliderList = sliderList;
        this.clickLisner = clickListener;
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_token_slider, null);

        TextView tvPatientName = view.findViewById(R.id.tvPatientName);
        TextView tvOPDName = view.findViewById(R.id.tvOPDName);
        TextView tvYourToken = view.findViewById(R.id.tvYourToken);
        TextView tvCurrentToken = view.findViewById(R.id.tvCurrentToken);
        ImageView ivRefresh = view.findViewById(R.id.ivRefresh);
        RelativeLayout rlRoot = view.findViewById(R.id.rlRoot);

        tvPatientName.setText(sliderList.get(position).getPatient());
        tvOPDName.setText("OPD Title : "+sliderList.get(position).getOpdTitle());
        tvYourToken.setText("Your Token : "+sliderList.get(position).getAppointmentToken());
        tvCurrentToken.setText("Current Token : "+sliderList.get(position).getCurrentToken());

        ivRefresh.setOnClickListener(clickLisner);
        rlRoot.setTag(position);
        rlRoot.setOnClickListener(clickLisner);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
