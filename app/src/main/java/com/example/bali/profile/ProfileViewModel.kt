package com.example.bali.profile

import android.net.Uri
import android.widget.Toast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ProfileViewModel : ViewModel() {

    private val _profilePhotoUrl = MutableLiveData<String>()
    val profilePhotoUrl: LiveData<String> get() = _profilePhotoUrl

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val storage = FirebaseStorage.getInstance()
    private val currentUser = FirebaseAuth.getInstance().currentUser

    fun fetchProfilePhoto(defaultPhotoUri: Uri) {
        if (currentUser != null) {
            val userId = currentUser.uid
            val photoRef = storage.reference.child("profile_photos/$userId.jpg")

            // Check if the profile photo exists
            photoRef.metadata.addOnSuccessListener { metadata ->
                if (metadata != null && metadata.sizeBytes > 0) {
                    // Profile photo exists, fetch its download URL
                    photoRef.downloadUrl.addOnSuccessListener { uri ->
                        _profilePhotoUrl.value = uri.toString()
                    }.addOnFailureListener {
                        // Handle failure to fetch download URL
                        _profilePhotoUrl.value = defaultPhotoUri.toString()
                    }
                } else {
                    // Profile photo doesn't exist, use default photo
                    _profilePhotoUrl.value = defaultPhotoUri.toString()
                }
            }.addOnFailureListener {
                // Handle failure to retrieve metadata
                _profilePhotoUrl.value = defaultPhotoUri.toString()
            }
        } else {
            // Handle the case when currentUser is null (user not logged in)
            println("currentUser is null")
        }
    }


    fun fetchUserName() {
        currentUser?.uid?.let { userId ->
            val databaseRef = FirebaseDatabase.getInstance().getReference("Users").child(userId)
            databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.child("fullName").value.toString()
                    // Update UI with the retrieved name
                    _userName.value = name
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error

                }
            })
        }
    }
    fun fetchUserEmail(): String? {
        return currentUser?.email
    }
    fun uploadProfileImage(userMetaData: UserMetaData, imageUri: Uri) {
        val userId = currentUser?.uid
        userId?.let {
            val photoRef = storage.reference.child("profile_photos/$userId.jpg")
            photoRef.putFile(imageUri)
                .addOnSuccessListener {
                    // Photo upload successful, fetch its download URL
                    photoRef.downloadUrl.addOnSuccessListener { uri ->
                        // Update user's profile photo URL in Firebase Realtime Database
                        val databaseRef = FirebaseDatabase.getInstance().getReference("Users").child(userId)
                        databaseRef.child("profilePhoto").setValue(uri.toString())
                            .addOnSuccessListener {
                                // Profile photo URL updated successfully
                                _profilePhotoUrl.value = uri.toString()
                            }.addOnFailureListener {
                                // Handle failure to update profile photo URL in database
                            }
                    }
                }
                .addOnFailureListener {
                    // Handle failure to upload photo
                }
        }
    }

    fun updateUserName(userMetaData: UserMetaData, newName: String) {
        val userId = currentUser?.uid
        userId?.let {
            val databaseRef = FirebaseDatabase.getInstance().getReference("Users").child(userId)
            databaseRef.child("fullName").setValue(newName)
                .addOnSuccessListener {
                    // User name updated successfully
                    _userName.value = newName
                }
                .addOnFailureListener {
                    // Handle failure to update user name
                }
        }
    }




}
