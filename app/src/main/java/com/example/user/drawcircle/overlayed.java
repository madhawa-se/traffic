package com.example.user.drawcircle;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class overlayed extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private CircleMarker circleMarker=new CircleMarker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlayed);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bitmap bitmap = circleMarker.GetBitmapMarker(getApplicationContext(), R.drawable.rose50, "1");
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.86491, 79.89968);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(6.8944042,79.8835175))
                .title("Hello world")
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        );

        mMap.addMarker(new MarkerOptions().position(sydney).title("nawala my titel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
