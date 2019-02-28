package com.melayer.myapplication.viewModels

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.melayer.myapplication.models.NicePlaces
import com.melayer.myapplication.repositories.NicePlacesRepository
import java.util.Objects

class MainActivityViewModel : ViewModel() {

    private var mNicePlaces: MutableLiveData<List<NicePlaces>>? = null
    private var mRepo: NicePlacesRepository? = null
    private val mIsUpdating = MutableLiveData<Boolean>()

    val nicePlaces: LiveData<List<NicePlaces>>?
        get() = mNicePlaces

    val isUpdating: LiveData<Boolean>?
        get() = mIsUpdating

    fun init() {
        if (mNicePlaces != null) {
            return
        }
        mRepo = NicePlacesRepository.getInstance()
        mNicePlaces = mRepo?.nicePlaces
    }

    @SuppressLint("StaticFieldLeak")
    fun addNewValue(nicePlaces: NicePlaces) {
        mIsUpdating.value = true

        object : AsyncTask<Void, Void, Void>() {

            override fun onPostExecute(aVoid: Void) {
                super.onPostExecute(aVoid)
                val currentPlaces  = mNicePlaces!!.value
                Objects.requireNonNull<ArrayList<NicePlaces>>(currentPlaces as ArrayList<NicePlaces>?).add(nicePlaces)
                mNicePlaces!!.postValue(currentPlaces)
                mIsUpdating.postValue(false)
            }

            override fun doInBackground(vararg voids: Void): Void? {
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                return null
            }
        }.execute()
    }
}