package com.github.ws.retrofitview.rest;

import com.github.ws.retrofitview.serviece.RestService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 5/17 0017.
 */
public class RestClient {

    private Retrofit mRetrofit;
    // private static final String BASE_URL = "http://zft.366ec.net";
   // private static final String BASE_URL = "http://jms-pic.b0.upaiyun.com";
   // private static final String BASE_URL = "https://github.com";

    private static final String BASE_URL = "http://plus.366ec.net";
    private RestService mService;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    public RestClient() {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        mService = mRetrofit.create(RestService.class);
    }

    public RestService getRectService() {
        if (mService != null) {
            return mService;
        }
        return null;
    }

}
