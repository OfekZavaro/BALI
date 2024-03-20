package com.example.bali.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bali.database.entities.Place


@Dao
interface PlaceDao {
    @Insert
    fun insertPlace(place: Place)
    @Query("SELECT * FROM places")
    fun getAllPlaces(): LiveData<List<Place>>

    @Query("SELECT * FROM places WHERE id = :placeId")
    fun getPlaceById(placeId: Int): LiveData<Place>

    @Query("SELECT * FROM places WHERE name = :placeName")
    fun getPlaceById(placeName: String): LiveData<Place>
}