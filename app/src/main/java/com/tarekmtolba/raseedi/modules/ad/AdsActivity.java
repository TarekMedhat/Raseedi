package com.tarekmtolba.raseedi.modules.ad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.tarekmtolba.raseedi.R;
import com.tarekmtolba.raseedi.data.model.Ad;
import com.tarekmtolba.raseedi.modules.addetails.AdDetailsActivity;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsActivity extends AppCompatActivity implements AdsView, AdsAdapter.ListItemClickListener {

    @BindView(R.id.ads_recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private AdsPresenter mPresenter;
    public static final String CLICKED_AD_KEY = "clicked_ad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);
        mPresenter = new AdsPresenter(this);
        mPresenter.listAds();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.listAds();
            }
        });
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateRecyclerView(List<Ad> ads) {
        swipeRefreshLayout.setRefreshing(false);
        mRecyclerview.setAdapter(new AdsAdapter(ads, AdsActivity.this));
    }

    @Override
    public void showErrorSnackBar() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(getWindow().getDecorView().getRootView(),getString(R.string.error_message),Snackbar.LENGTH_INDEFINITE)
        .setAction(getString(R.string.retry), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.listAds();
            }
        })
        .show();
    }

    @Override
    public void onListItemClick(Ad clickedAd) {
        Intent intent = new Intent(this, AdDetailsActivity.class);
        intent.putExtra(CLICKED_AD_KEY, clickedAd);
        startActivity(intent);
    }
}
