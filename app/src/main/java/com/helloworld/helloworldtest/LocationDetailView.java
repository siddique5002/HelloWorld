package com.helloworld.helloworldtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

import Model.Office;

public class LocationDetailView extends AppCompatActivity {
    GoogleMap mMap;
    private Office office;
    private TextView name;
    private TextView address;
    private TextView distance;
    private TextView phonenumber;
    private NetworkImageView officeImage;
    private Button callnow;
    private Button button2Direction;
    GPSTracker gpsTracker;
    double latitude;
    double longitude;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail_view);

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        gpsTracker = new GPSTracker(this);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();

        office = (Office) getIntent().getSerializableExtra("officeObj");

        name = (TextView) findViewById(R.id.OfficenameD);
        address = (TextView) findViewById(R.id.OfficeAddressD);
        phonenumber = (TextView) findViewById(R.id.phonenumber);
        distance = (TextView) findViewById(R.id.DistanceD);
        button2Direction = (Button) findViewById(R.id.button2Direction);
        callnow = (Button) findViewById(R.id.callnow);

        officeImage = (NetworkImageView) findViewById(R.id.officeImage);


        name.setText("Name: " + office.getOfficename());
        address.setText("Address: " + office.getAddress());
        distance.setText("Distance: " + new DecimalFormat("##.##").format(office.getDistance()) + "mile");
        phonenumber.setText(office.getPhone());
        officeImage.setImageUrl(office.getImage(), imageLoader);


        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(LocationDetailView.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocationDetailView.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);

                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + office.getPhone()));
                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(callIntent);
                }
            }

        });
        button2Direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?" +
                        "saddr=" + latitude + "," + longitude + "&daddr=" + office.getLatitude() + "," +
                        office.getLongitude()));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);

            }
        });
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(office.getLatitude()), Double.parseDouble(office.getLongitude())), 16));
// add a marker to the map indicating our current position
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.parseDouble(office.getLatitude()), Double.parseDouble(office.getLongitude()))).title(office.getOfficename())
                .snippet(office.getAddress()));

    }


}
