package com.example.bali.homePage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bali.database.DatabaseInstance
import com.example.bali.database.dao.PlaceDao
import com.example.bali.database.entities.Place

class HomePageViewModel(application: Application) : AndroidViewModel(application) {

    private val placeDao: PlaceDao = DatabaseInstance.getDatabase(application).placeDao()

    val places: LiveData<List<Place>> = placeDao.getAllPlaces()
}