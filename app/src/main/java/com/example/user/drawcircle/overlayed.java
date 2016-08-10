package com.example.user.drawcircle;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class overlayed extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Context context;
    private CircleMarker circleMarker = new CircleMarker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overlayed);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = getApplicationContext();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bitmap bitmap = circleMarker.GetBitmapMarker(getApplicationContext(), R.drawable.rose50, "1");
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng nugegoda = new LatLng(6.8944042, 79.8835175);
        mMap.addMarker(new MarkerOptions()
                .position(nugegoda)
                .title("nugegoda T")
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        );


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("gggggggggggggggggggg", "nooooooooooooooooooooooooooooooooo");


                AlertDialog.Builder builder = new AlertDialog.Builder(overlayed.this, R.style.MyAlertDialogStyle);

                builder.setTitle("Rate the traffic");
                builder.setView(R.layout.activity_ustom);
                // builder.setPositiveButton("Rate", null);
                //builder.setNegativeButton("Cancel", null);


                TextView title = new TextView(overlayed.this);
// You Can Customise your Title here
                title.setText("Rate the traffic");
                //title.setBackgroundColor(Color.DKGRAY);
                title.setPadding(10, 5, 5, 5);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.WHITE);
                title.setTextSize(20);

                //builder.setCustomTitle(title);


                builder.show();


                return true;
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(nugegoda));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }
}
