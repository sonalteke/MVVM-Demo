package com.melayer.myapplication.repositories

import android.arch.lifecycle.MutableLiveData
import com.melayer.myapplication.models.NicePlaces

import java.util.ArrayList

class NicePlacesRepository {
    private val dataSet = ArrayList<NicePlaces>()

    val nicePlaces: MutableLiveData<ArrayList<NicePlaces>>?
        get() {
            setNicePlaces()
            val data = MutableLiveData<ArrayList<NicePlaces>>()
            data.value = dataSet
            return data
        }

    private fun setNicePlaces() {
        dataSet.add(NicePlaces("Havasu Falls", "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"))
        dataSet.add(NicePlaces("Trondheim", "https://i.redd.it/tpsnoz5bzo501.jpg"))
        dataSet.add(NicePlaces("Portugal", "https://i.redd.it/qn7f9oqu7o501.jpg"))
        dataSet.add(NicePlaces("Rocky Mountain National Park", "https://i.redd.it/j6myfqglup501.jpg"))
        dataSet.add(NicePlaces("Havasu Falls", "https://i.redd.it/0h2gm1ix6p501.jpg"))
        dataSet.add(NicePlaces("Mahahual", "https://i.redd.it/k98uzl68eh501.jpg"))
        dataSet.add(NicePlaces("Frozen Lake", "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg"))
        dataSet.add(NicePlaces("Australia", "https://i.redd.it/obx4zydshg601.jpg"))
    }

    companion object {

        private var instance: NicePlacesRepository? = null

        fun getInstance(): NicePlacesRepository {
            if (instance == null) {
                instance = NicePlacesRepository()
            }
            return instance as NicePlacesRepository
        }
    }
}
