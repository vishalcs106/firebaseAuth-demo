package com.android.firebasedemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.firebasedemo.R;
import com.android.firebasedemo.model.Place;
import com.android.firebasedemo.presenter.PlacesPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends BaseActivity implements OnMapReadyCallback, PlacesViewInterface {
    GoogleMap mMap;
    Location location;
    LocationManager locationManager;
    static final int LOCATION_PERMISSION_REQUEST_CODE = 10;
    PlacesPresenter placesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        placesPresenter = new PlacesPresenter(PlacesActivity.this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        location = getLocation();
        initializeMap();
    }

    private void initializeMap() {
        if (mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().
                    findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(location != null) {
            locateMe();
        } else {
            showToast("Could not locate you");
        }
    }

    private void locateMe() {
        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                currentLatLng).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().position(currentLatLng).icon(BitmapDescriptorFactory
                .fromResource(R.drawable.current_location_map_pointer_small)));
        getNearbyGasStations();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        if(hasPermission(android.Manifest.permission.
                ACCESS_FINE_LOCATION)){
            location = accurateLocation();
        }
         else {
            requestPermissionsSafely(new String[]{android.Manifest.permission.
                    ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
        return location;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                location = accurateLocation();
                if(location != null){
                    locateMe();
                } else {
                    showToast("Could not locate you");
                }
            }
        }
    }

    private Location accurateLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    private void getNearbyGasStations() {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +
                location.getLatitude()+","+location.getLongitude()+
                "&radius=10000&rank_by=distance&type=gas_station&key=" +
                "AIzaSyDrkRmAYiVl6v9iQrBcvtgEwGJdPs06Ai4";
        placesPresenter.getPlaces(location.getLatitude()+","+location.getLongitude());
    }

    @Override
    public void onPlacesSuccess(ArrayList<Place> result) {
        for(Place place : result){
            LatLng newLatLng = new LatLng(place.getGeometry().getLocation().getLat(),
                    place.getGeometry().getLocation().getLng());
            mMap.addMarker(new MarkerOptions().position(newLatLng).icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_gas)));
        }
    }

    @Override
    public void onPlacesFailure(String message) {
        showToast(message);
    }
}
