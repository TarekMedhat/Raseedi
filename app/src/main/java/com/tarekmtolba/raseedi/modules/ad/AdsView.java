package com.tarekmtolba.raseedi.modules.ad;

import com.tarekmtolba.raseedi.data.model.Ad;

import java.util.List;

public interface AdsView {
    void hideProgressBar();
    void updateRecyclerView(List<Ad> ads);
    void showErrorSnackBar();
}
