package com.android.firebasedemo.dagger.modules;

import com.android.firebasedemo.dagger.CustomScope;
import com.android.firebasedemo.model.PlacesApi;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Vishal on 20-12-2017.
 */

@Module
public class ApiModule {
    @Provides
    @CustomScope
    FirebaseAuth providesFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Provides
    @CustomScope
    PlacesApi providesPlacesApi(Retrofit retrofit){
        return retrofit.create(PlacesApi.class);
    }

    @Provides
    @CustomScope
    Gson providesGson(){
        return new Gson();
    }

}
