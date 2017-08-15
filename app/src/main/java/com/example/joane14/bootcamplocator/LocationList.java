package com.example.joane14.bootcamplocator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LocationList extends Fragment {

    public LocationList() {
    }


    public static LocationList newInstance() {
        LocationList fragment = new LocationList();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location_list, container, false);
        //creating our recycler view
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_container);
        recyclerView.setHasFixedSize(true);
        //setting the adapter on the recylerview and getting location list from the data services ...
        LocationAdapter locationAdapter = new LocationAdapter(DataService.getInstance().getNearBootCampLocations(9000));
        recyclerView.setAdapter(locationAdapter);
        //Setting orientation for recycler view...
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //Setting the layout for the recylerview...
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }


}
