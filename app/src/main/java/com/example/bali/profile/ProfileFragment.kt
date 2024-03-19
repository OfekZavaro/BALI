package com.example.bali.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bali.R
import com.example.bali.Welcome
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var profileImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var logoutButton: Button
    private lateinit var editProfileButton: ImageView
    private lateinit var auth: FirebaseAuth

    private var currentName: String? = null
    private var currentProfilePicUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_profile, container, false
        )
        profileImageView = view.findViewById(R.id.profileImageView)
        nameTextView=view.findViewById(R.id.nameTextView)
        emailTextView=view.findViewById(R.id.emailTextView)
        logoutButton=view.findViewById(R.id.logoutButton)
        editProfileButton=view.findViewById(R.id.editProfileButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProfilePhoto()
        observeUserData()
        auth = FirebaseAuth.getInstance()
        logoutButton.setOnClickListener {
            logoutUser()
        }

        editProfileButton.setOnClickListener {
            editProfile()
        }
        // Fetch and display profile photo
        val defaultPhotoResourceId = R.drawable.profile_photo_placeholder
        val defaultPhotoUri = Uri.parse("android.resource://${requireContext().packageName}/$defaultPhotoResourceId")

        viewModel.fetchProfilePhoto(defaultPhotoUri)
        viewModel.fetchUserName()
        viewModel.fetchUserEmail()
    }

    private fun observeProfilePhoto() {
        viewModel.profilePhotoUrl.observe(viewLifecycleOwner) { photoUrl ->
            Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.profile_photo_placeholder) // Placeholder image while loading
                .error(R.drawable.profile_photo_placeholder) // Image to show in case of error
                .into(profileImageView as ImageView)
        }
    }

    private fun observeUserData() {
        viewModel.userName.observe(viewLifecycleOwner) { name ->
            // Update UI with user's name
            nameTextView.text = name
            // Store the current name for editing profile
            currentName = name
        }

        val userEmail = viewModel.fetchUserEmail()
        // Update UI with user's email
        emailTextView.text = userEmail
    }

    private fun logoutUser() {
        auth.signOut()
        // navigate the user to welcome activity
        val intent = Intent(requireContext(), Welcome::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }
    private fun editProfile() {
        val dialogFragment = EditProfileDialogFragment().apply {
            arguments = Bundle().apply {
                putString("currentName", currentName)
                putString("currentProfilePicUrl", currentProfilePicUrl)
            }
        }
        dialogFragment.show(parentFragmentManager, "EditProfileDialogFragment")
    }

}
