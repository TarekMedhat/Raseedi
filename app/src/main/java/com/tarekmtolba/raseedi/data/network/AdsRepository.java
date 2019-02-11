package com.tarekmtolba.raseedi.data.network;


import com.tarekmtolba.raseedi.api.ApiServiceUtil;
import com.tarekmtolba.raseedi.api.RaseediService;
import com.tarekmtolba.raseedi.data.model.Ad;
import com.tarekmtolba.raseedi.data.usecase.GetAdsMvpUseCase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdsRepository  {

    private final RaseediService webservice;
    private final Executor executor;
    private GetAdsMvpUseCase getAdsMvpUseCase;

    public AdsRepository(GetAdsMvpUseCase getAdsMvpUseCase) {
        this.executor = Executors.newSingleThreadExecutor();
        this.webservice = ApiServiceUtil.getRaseediService();
        this.getAdsMvpUseCase = getAdsMvpUseCase;
    }

    public void fetchAds() {
        executor.execute(() -> {
                webservice.getAds().enqueue(new Callback<List<Ad>>() {
                    @Override
                    public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
                        executor.execute(() -> {
                            if(response.isSuccessful()) {
                                List<Ad> ads = response.body();
                                getAdsMvpUseCase.listAds(ads);
                            }
                            else{
                                getAdsMvpUseCase.onError();
                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<List<Ad>> call, Throwable t) {
                        getAdsMvpUseCase.onError();
                    }
                });
        });
    }
}
