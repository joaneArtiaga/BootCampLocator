package com.example.joane14.bootcamplocator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Joane14 on 14/08/2017.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    private MarkerOptions userMarker;
    private String zip;
    private LocationList locationsListFragments;


    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationsListFragments =  (LocationList) getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container_location_list);
        if(locationsListFragments == null){
            locationsListFragments = LocationList.newInstance();
            getActivity().
                    getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.container_location_list, locationsListFragments).
                    commit();
        }

        final EditText zipText = (EditText) v.findViewById(R.id.zip_text);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    zip = zipText.getText().toString();
                    Toast.makeText(getContext(), zip, Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(zipText.getWindowToken(), 0);
                    updateMapForZip(zip);
                    showList();
                    return true;
                }
                return false;
            }
        });
        hideList();
        return v;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

    }

    public void setUserMarkers(LatLng latLng) {
        if (userMarker == null) {
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mGoogleMap.addMarker(userMarker);
        }

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        mGoogleMap.setTrafficEnabled( true );

    }


    private void updateMapForZip(String zip_code){

        Toast.makeText(getContext(), zip_code, Toast.LENGTH_SHORT).show();
        ArrayList<LocationObject> locations = DataService.getInstance().getNearBootCampLocations(Integer.parseInt(zip_code));

        for (int x = 0; x < locations.size(); x++){
            LocationObject loc = locations.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()));
            marker.title(loc.getLocationTittle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mGoogleMap.addMarker(marker);
        }

    }

    private void hideList(){
        getActivity().getSupportFragmentManager().beginTransaction().hide(locationsListFragments).commit();
    }

    private void showList(){
        getActivity().getSupportFragmentManager().beginTransaction().show(locationsListFragments).commit();
    }

}
