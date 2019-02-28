package com.melayer.firstimagedemo.repositories;

import android.arch.lifecycle.MutableLiveData;
import com.melayer.firstimagedemo.models.NicePlaces;

import java.util.ArrayList;
import java.util.List;

public class NicePlacesRepository {

    private static NicePlacesRepository instance;
    private ArrayList<NicePlaces> dataset = new ArrayList<>();

    public static NicePlacesRepository getInstance(){
        if(instance == null){
            instance = new NicePlacesRepository();
        }
        return instance;
    }

    public MutableLiveData<List<NicePlaces>> getNicePlaces(){
        setNicePlaces();
        MutableLiveData<List<NicePlaces>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setNicePlaces(){
        dataset.add( new NicePlaces("Havasu Falls","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        dataset.add(new NicePlaces("Trondheim", "https://i.redd.it/tpsnoz5bzo501.jpg"));
        dataset.add(new NicePlaces("Portugal","https://i.redd.it/qn7f9oqu7o501.jpg"));
        dataset.add(new NicePlaces("Rocky Mountain National Park","https://i.redd.it/j6myfqglup501.jpg"));
        dataset.add(new NicePlaces("Havasu Falls","https://i.redd.it/0h2gm1ix6p501.jpg"));
        dataset.add(new NicePlaces("Mahahual","https://i.redd.it/k98uzl68eh501.jpg"));
        dataset.add(new NicePlaces("Frozen Lake","https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"));
        dataset.add(new NicePlaces("Austrailia","https://i.redd.it/obx4zydshg601.jpg"));
    }
}
