package com.android.firebasedemo.dagger.modules;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vishal on 20-12-2017.
 */

@Module
public class NetworkModule {
    public NetworkModule(){

    }
    @Provides
    @Singleton
    RxJava2CallAdapterFactory providesRxJavaCallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }
    @Provides
    @Singleton
    GsonConverterFactory providesGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory,
                              RxJava2CallAdapterFactory rxJavaCallAdapterFactory){
        return new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(okHttpClient).baseUrl("https://maps.googleapis.com/").build();
    }
}
