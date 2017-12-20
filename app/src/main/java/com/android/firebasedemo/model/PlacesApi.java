package com.android.firebasedemo.model;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Vishal on 20-12-2017.
 */

public interface PlacesApi {
    @GET("maps/api/place/nearbysearch/json")
    Observable<JsonObject> getGasStations(@Query("location") String location,
                                          @Query("radius") String radius,
                                          @Query("rank_by") String rankBy,
                                          @Query("type") String type,
                                          @Query("key") String key);
}
