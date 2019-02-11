package com.tarekmtolba.raseedi.api;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceUtil {

    private static RaseediService raseediService = null;
    /**
     * returns a web service instance of raseediwebservice
     */
    public static RaseediService getRaseediService() {

        if (raseediService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .callTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();


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
