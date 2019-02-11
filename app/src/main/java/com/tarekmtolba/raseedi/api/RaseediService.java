package com.tarekmtolba.raseedi.api;

import com.tarekmtolba.raseedi.data.model.Ad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RaseediService {
    String baseURL = "https://simswitch.bit68.com";

    /**
     * @GET declares an HTTP GET request
     * returns a list of ads
     */
    @GET("/get_ad/?solo=false")
    Call<List<Ad>> getAds();

}