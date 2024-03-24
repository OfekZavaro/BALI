package com.example.bali.places

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bali.database.DatabaseInstance
import com.example.bali.database.entities.Place

class PlaceViewModel(application: Application) : AndroidViewModel(application) {
    private val placeDao = DatabaseInstance.getDatabase(application).placeDao()


    fun getPlaceById(placeId: Int): LiveData<Place> {
        return placeDao.getPlaceById(placeId)
    }

}