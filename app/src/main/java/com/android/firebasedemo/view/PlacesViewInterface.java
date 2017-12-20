package com.android.firebasedemo.view;

import com.android.firebasedemo.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal on 20-12-2017.
 */

public interface PlacesViewInterface {
    void onPlacesSuccess(ArrayList<Place> result);
    void onPlacesFailure(String message);
}
