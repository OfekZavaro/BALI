package com.example.bali.shared

import androidx.lifecycle.ViewModel
import com.example.bali.profile.UserMetaData


class SharedViewModel : ViewModel() {
    var userMetaData: UserMetaData = UserMetaData(fullName = "",
        email = "", profilePhoto = "")
}