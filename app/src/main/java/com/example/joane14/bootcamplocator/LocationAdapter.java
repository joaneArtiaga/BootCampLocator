package com.example.joane14.bootcamplocator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Joane14 on 14/08/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationHolder>{

    private ArrayList<LocationObject> locations;
    public LocationAdapter(ArrayList<LocationObject> locations) {
        this.locations = locations;
    }

    @Override
    public void onBindViewHolder(LocationHolder holder, int position) {
        final LocationObject location = locations.get(position);
        holder.updateUI(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load
                // Details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new LocationHolder(card);
    }

}