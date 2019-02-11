package com.tarekmtolba.raseedi.data.usecase;

import com.tarekmtolba.raseedi.data.model.Ad;
import com.tarekmtolba.raseedi.data.network.AdsRepository;
import com.tarekmtolba.raseedi.modules.ad.AdsMvpPresenter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GetGetAdsUseCase implements GetAdsMvpUseCase {
    private AdsMvpPresenter adsMvpPresenter;

    public GetGetAdsUseCase(AdsMvpPresenter adsMvpPresenter) {
        this.adsMvpPresenter = adsMvpPresenter;
    }

    public void fetchAds() {
       AdsRepository adsRepository = new AdsRepository(this);
       adsRepository.fetchAds();
    }

    @Override
    public void listAds(List<Ad> ads) {
        //Sort ads in order
        Collections.sort(ads, new Comparator<Ad>() {
            @Override
            public int compare(Ad a1, Ad a2) {
                return a1.getOrder() - a2.getOrder();
            }
        });
        adsMvpPresenter.getSortedAds(ads);

    }

    @Override
    public void onError() {
        adsMvpPresenter.onError();
    }
}
