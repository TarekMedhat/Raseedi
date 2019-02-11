package com.tarekmtolba.raseedi.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceUtil {

    private static RaseediService raseediService = null;

    public static RaseediService getRaseediService() {

        if (raseediService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RaseediService.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            raseediService = retrofit.create(RaseediService.class);
        }
        return raseediService;
    }
}
