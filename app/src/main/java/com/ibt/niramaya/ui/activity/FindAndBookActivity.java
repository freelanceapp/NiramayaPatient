package com.ibt.niramaya.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseActivity;

public class FindAndBookActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_and_book);

        init();
    }

    private void init() {
        ((TextView) findViewById(R.id.txtTitle)).setText(getString(R.string.find_and_book));
        findViewById(R.id.imgFilter).setVisibility(View.VISIBLE);
    }
}
