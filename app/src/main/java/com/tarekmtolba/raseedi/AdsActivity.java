package com.tarekmtolba.raseedi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsActivity extends AppCompatActivity {

    @BindView(R.id.ads_recyclerview) RecyclerView adsRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        ButterKnife.bind(this);

        
    }
}
