package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.home.Appslider;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Appslider> searchArrayList;
    private View.OnClickListener onClickListener;

    public ViewPagerAdapter(Context context, List<Appslider> searchArrayList, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.searchArrayList = searchArrayList;
        this.onClickListener = onClickListener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.slide_show_pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //imageView.setImageResource(searchArrayList.get(position));

        Glide.with(mContext)
                .load(searchArrayList.get(position).getImage())
                .fitCenter()
                .into(imageView);

        /*imageView.setTag(position);
        imageView.setOnClickListener(onClickListener);*/
        //String strUrl = searchArrayList.get(position).getOfferPicture();
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
