package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ibt.niramaya.R;

public class PeditricianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peditrician);

        init();
    }

    private void init() {
        findViewById(R.id.imgSearch).setVisibility(View.VISIBLE);
        findViewById(R.id.imgFilter).setVisibility(View.VISIBLE);
    }
}
