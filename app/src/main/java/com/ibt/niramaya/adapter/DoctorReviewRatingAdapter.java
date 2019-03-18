package com.ibt.niramaya.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ibt.niramaya.R;

import java.util.List;

public class DoctorReviewRatingAdapter extends RecyclerView.Adapter<DoctorReviewRatingAdapter.ViewHolder> {
    private Context mcontext;
    private List<String> reviewList;

    public DoctorReviewRatingAdapter(Context mcontext,List<String> reviewList) {
        this.mcontext = mcontext;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(mcontext);
        View rootview = li.inflate(R.layout.row_doctor_review_list,null);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_review_profile;
        private TextView tvName,tvTime,tvDescription;
        private RatingBar rvRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_review_profile = itemView.findViewById(R.id.iv_review_profile);
            tvName = itemView.findViewById(R.id.tv_review_person_name);
            tvTime = itemView.findViewById(R.id.tv_review_time);
            tvDescription = itemView.findViewById(R.id.tv_review_description);
        }
    }
}
