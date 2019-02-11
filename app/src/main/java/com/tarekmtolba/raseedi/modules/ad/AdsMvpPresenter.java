package com.tarekmtolba.raseedi.modules.ad;

import com.tarekmtolba.raseedi.data.model.Ad;

import java.util.List;

public interface AdsMvpPresenter {
    void listAds();
    void getSortedAds(List<Ad> ads);
    void onError();
}
