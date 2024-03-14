package com.example.bali.utils

import android.app.Application
import com.example.bali.database.DatabaseInstance
import com.example.bali.database.entities.Place
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Utils {
    fun initializeDatabaseWithPlace(application: Application) {
        // Define the reference to your image in Firebase Storage
        val storageReference = FirebaseStorage.getInstance().getReference("TaderPlace.png")
        // Start the download of the URL
        storageReference.downloadUrl.addOnSuccessListener { downloadUri ->
            val imageUrl = downloadUri.toString()

            val place = Place(
                name = "Tader Place3",
                address = "Some Address3",
                description = "Some Description3",
                placePhoto = imageUrl
            )
            // Get your Room database and Dao
            val db = DatabaseInstance.getDatabase(application)
            val placeDao = db.placeDao()

            CoroutineScope(Dispatchers.IO).launch {
                placeDao.insertPlace(place)
            }
        }.addOnFailureListener {
            // Handle any errors in case the download URL could not be retrieved
        }
    }

}