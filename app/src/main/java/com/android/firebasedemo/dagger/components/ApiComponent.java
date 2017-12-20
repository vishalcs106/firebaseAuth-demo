package com.android.firebasedemo.dagger.components;

import com.android.firebasedemo.dagger.CustomScope;
import com.android.firebasedemo.dagger.modules.ApiModule;
import com.android.firebasedemo.model.PlacesApi;
import com.android.firebasedemo.presenter.LoginPresenter;
import com.android.firebasedemo.presenter.PlacesPresenter;
import com.android.firebasedemo.view.MainActivity;

import dagger.Component;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Vishal on 20-12-2017.
 */

@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
@CustomScope
public interface ApiComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginPresenter loginPresenter);

    void inject(PlacesPresenter placesPresenter);
}
