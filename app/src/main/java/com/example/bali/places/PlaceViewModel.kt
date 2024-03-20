package com.example.bali.places

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bali.database.DatabaseInstance
import com.example.bali.database.dao.PlaceDao
import com.example.bali.database.entities.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PlaceViewModel(application: Application) : AndroidViewModel(application) {
    private val placeDao = DatabaseInstance.getDatabase(application).placeDao()

    fun getPlaceById(placeId: Int): LiveData<Place> {
        return placeDao.getPlaceById(placeId)
    }
}