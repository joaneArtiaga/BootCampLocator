package com.example.joane14.bootcamplocator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joane14 on 14/08/2017.
 */

public class LocationHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;


    public LocationHolder(View itemView) {
        super(itemView);

        locationImage = (ImageView)itemView.findViewById(R.id.ivLocation);
        locationTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        locationAddress = (TextView)itemView.findViewById(R.id.tvAddress);
    }

    public void updateUI (LocationObject location){

        String uri = location.getLocationImgUrl();

        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());

        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTittle());
        locationAddress.setText(location.getLocationAddress());
    }
}