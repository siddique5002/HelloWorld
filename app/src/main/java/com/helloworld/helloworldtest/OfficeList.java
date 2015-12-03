package com.helloworld.helloworldtest;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Data.CustomListviewAdapter;
import Model.Office;

public class OfficeList extends AppCompatActivity {

    private CustomListviewAdapter adapter;
    private ArrayList<Office> offices = new ArrayList<>();
    private ListView listView;
    GPSTracker gpsTracker;

    double latitude;
    double longitude;

    Context mContext;

    private String url = "http://www.helloworld.com/helloworld_locations.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_list);
        mContext = this;
        gpsTracker = new GPSTracker(mContext);
        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

        } else {
            gpsTracker.showSettingsAlert();

        }

        listView = (ListView) findViewById(R.id.officelistview);

        // Get office list from this method

        GetOffice();

        // here set my custom list view Adapter

        adapter = new CustomListviewAdapter(OfficeList.this, R.layout.row_list, offices);
        listView.setAdapter(adapter);


    }

    private void GetOffice() {
        offices.clear();

        JsonObjectRequest officeRequest = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray movielistArray = response.getJSONArray("locations");




                    for (int i = 0; i < movielistArray.length(); i++) {
                        JSONObject jsonObject = movielistArray.getJSONObject(i);


                        String name = jsonObject.getString("name");
                        String address = jsonObject.getString("address");
                        String address2 = jsonObject.getString("address2");
                        String city = jsonObject.getString("city");
                        String state = jsonObject.getString("state");
                        String zip = jsonObject.getString("zip_postal_code");
                        String phone = jsonObject.getString("phone");
                        String fax = jsonObject.getString("fax");
                        String latitude = jsonObject.getString("latitude");
                        String longitude = jsonObject.getString("longitude");
                        String office_image = jsonObject.getString("office_image");

                        //Log.v("Data", office_image );


                        Office office = new Office();
                        office.setOfficename(name);
                        office.setAddress(address);
                        office.setAddress2(address2);
                        office.setCity(city);
                        office.setState(state);
                        office.setZip(zip);
                        office.setPhone(phone);
                        office.setFax(fax);
                        office.setLatitude(latitude);
                        office.setLongitude(longitude);
                        office.setImage(office_image);
                        office.setDistance(getDistance(Double.parseDouble(latitude), Double.parseDouble(longitude)));

                        offices.add(office);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(officeRequest);
    }

    //

    public double getDistance(double latitud, double longit) {
        Location selected_location = new Location("locationA");
        selected_location.setLatitude(latitude);
        selected_location.setLongitude(longitude);
        Location near_locations = new Location("locationb");
        near_locations.setLatitude(latitud);
        near_locations.setLongitude(longit);

        return selected_location.distanceTo(near_locations) / 1609.34;
    }
}
