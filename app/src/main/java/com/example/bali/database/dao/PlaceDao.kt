package com.example.bali.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.bali.database.entities.Place

interface PlaceDao {
    @Insert
    fun insertPlace(place: Place)
    @Query("SELECT * FROM places")
    fun getAllPlaces(): List<Place>

    @Query("SELECT * FROM places WHERE id = :placeId")
    fun getPlaceById(placeId: Int): Place?

    @Query("SELECT * FROM places WHERE name = :placeName")
    fun getPlaceById(placeName: String): Place?
}