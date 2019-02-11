package com.tarekmtolba.raseedi.modules.ad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.tarekmtolba.raseedi.data.model.Ad;
import com.tarekmtolba.raseedi.data.usecase.GetGetAdsUseCase;

import java.util.List;


public class AdsPresenter implements AdsMvpPresenter {
    private AdsView mAdsView;
    private Activity mActivity;
    private GetGetAdsUseCase getAdsUseCase;
    public AdsPresenter(Activity activity) {
        mActivity = activity;
        this.mAdsView = (AdsView) activity;
    }

    @Override
    public void listAds() {
        getAdsUseCase = new GetGetAdsUseCase(this);
        getAdsUseCase.fetchAds();
    }

    @Override
    public void getSortedAds(List<Ad> ads) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdsView.hideProgressBar();
                mAdsView.updateRecyclerView(ads);
            }
        });

    }

    @Override
    public void onError() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdsView.hideProgressBar();
                mAdsView.showErrorSnackBar();
            }
        });
    }
}
