package com.melayer.firstimagedemo.viewModels;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import com.melayer.firstimagedemo.models.NicePlaces;
import com.melayer.firstimagedemo.repositories.NicePlacesRepository;

import java.util.List;
import java.util.Objects;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlaces>> mNicePlaces;
    private NicePlacesRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mNicePlaces != null){
            return;
        }
        mRepo = NicePlacesRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final NicePlaces nicePlaces){
        mIsUpdating.setValue(true);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlaces> curentPlaces = mNicePlaces.getValue();
                Objects.requireNonNull(curentPlaces).add(nicePlaces);
                mNicePlaces.postValue(curentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<NicePlaces>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}