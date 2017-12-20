package com.android.firebasedemo.dagger.components;

import com.android.firebasedemo.dagger.modules.NetworkModule;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Vishal on 20-12-2017.
 */

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}

