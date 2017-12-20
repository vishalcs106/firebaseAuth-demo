package com.android.firebasedemo.presenter;

import com.android.firebasedemo.Constants;
import com.android.firebasedemo.dagger.Injector;
import com.android.firebasedemo.model.Place;
import com.android.firebasedemo.model.PlacesApi;
import com.android.firebasedemo.view.PlacesViewInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vishal on 20-12-2017.
 */

public class PlacesPresenter {
    PlacesViewInterface viewInterface;
    @Inject
    PlacesApi apiService;
    @Inject
    Gson gson;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PlacesPresenter(PlacesViewInterface viewInterface){
        this.viewInterface = viewInterface;
        Injector.getApiComponent().inject(PlacesPresenter.this);
    }

    public void getPlaces(String location ){
        compositeDisposable.add(apiService.getGasStations(location, Constants.RADIUS,
                Constants.RANK_BY, Constants.TYPE, Constants.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        JsonArray resultsArray = jsonObject.getAsJsonArray(Constants.KEY_RESULTS);
                        ArrayList<Place> stations = new ArrayList<>();
                        for(int i=0;i<resultsArray.size();i++){
                            JsonObject stationJson = resultsArray.get(i).getAsJsonObject();
                            try {
                                Place fuelStation = gson.fromJson(stationJson, Place.class);
                                stations.add(fuelStation);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        viewInterface.onPlacesSuccess(stations);
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewInterface.onPlacesFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
