package com.android.firebasedemo.dagger;

import com.android.firebasedemo.dagger.components.ApiComponent;
import com.android.firebasedemo.dagger.components.DaggerApiComponent;
import com.android.firebasedemo.dagger.components.DaggerNetworkComponent;
import com.android.firebasedemo.dagger.components.NetworkComponent;
import com.android.firebasedemo.dagger.modules.NetworkModule;

/**
 * Created by Vishal on 19-12-2017.
 */

public class Injector {
    public static ApiComponent getApiComponent() {
        return DaggerApiComponent.builder().networkComponent(
                getNetworkComponent()).build();
    }
    public static NetworkComponent getNetworkComponent(){
        return DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }
}
