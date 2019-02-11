package com.tarekmtolba.raseedi.data.usecase;

import com.tarekmtolba.raseedi.data.model.Ad;

import java.util.List;

public interface GetAdsMvpUseCase {
    void listAds(List<Ad> ads);
    void onError();
}
