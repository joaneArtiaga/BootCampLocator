package com.example.joane14.bootcamplocator;

import java.util.ArrayList;

/**
 * Created by Joane14 on 14/08/2017.
 */

public class DataService {
    private static final DataService INSTANCE = new DataService();

    public static DataService getInstance() {
        return INSTANCE;
    }

    public DataService() {
    }

    public ArrayList<LocationObject> getNearBootCampLocations(int zipcode){
        ArrayList<LocationObject> bootCamps =new ArrayList<>();
        bootCamps.add(new LocationObject( 10.291317f, 123.864788f, "Jumalon Butterfly Sanctuary And Art Gallery", "Cebu City", "img"));
        bootCamps.add(new LocationObject( 10.289400f,123.866082f, "Don Vicente Rama Memorial Elementary School", "Cebu City", "img"));
        bootCamps.add(new LocationObject( 10.288396f,123.856164f, "Quiot Pardo Elementary School", "Cebu City", "img"));
        return bootCamps;
    }
}
