package com.example.user.drawcircle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;
    private Marker myMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("ggggggggggggggggggggggg", "nooooooooooooooooooooooooooooooooo");
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
       // mapFragment.getMapAsync(this);
        context = getApplicationContext();
        init();
    }

    private void init() {
        Log.i("ggggggggggggggggggggggg", "nooooooooooooooooooooooooooooooooo");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.86491, 79.89968);
        myMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.8944042, 79.8835175))
                .title("Hello world dude")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle_50)));

        mMap.addMarker(new MarkerOptions().position(sydney).title("nawala my titel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //////////////
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_ustom);
        dialog.setTitle("Titlexx...");
        dialog.show();
        //////////////
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("ggggggggggggggggggggggg", "nooooooooooooooooooooooooooooooooo");
                return true;
            }
        });
    }


}
