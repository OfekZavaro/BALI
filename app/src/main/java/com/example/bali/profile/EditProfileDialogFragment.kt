package com.example.bali.profile

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.bali.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso

class EditProfileDialogFragment : DialogFragment() {
    private lateinit var imageViewProfilePic: ImageView
    private lateinit var pickImageContract: ActivityResultLauncher<Intent>
    private lateinit var userMetaData: UserMetaData


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.fragment_edit_profile_dialog, null)

        val editTextProfileName = view.findViewById<EditText>(R.id.editTextProfileName)
         imageViewProfilePic = view.findViewById<ImageView>(R.id.imageViewProfilePic)
        val buttonSelectProfilePic = view.findViewById<Button>(R.id.buttonSelectProfilePic)
        // Initialize pickImageContract
        pickImageContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                selectedImageUri?.let {
                    // Load and display the selected image in the ImageView using Picasso
                    Picasso.get().load(selectedImageUri).into(imageViewProfilePic)
                    // Save the URI for future use if needed
                    // currentProfilePicUrl = selectedImageUri.toString()
                }
            }
        }
        // Receive data passed from ProfileFragment
        val currentName = arguments?.getString("currentName")
        val currentProfilePicUrl = arguments?.getString("currentProfilePicUrl")

        // Pre-fill the EditText field with the current name
        editTextProfileName.setText(currentName)

        // Load and display the current profile picture in the ImageView using Picasso
        currentProfilePicUrl?.let {
            Picasso.get().load(it).into(imageViewProfilePic)
        }

        // Set click listener for selecting profile picture
        buttonSelectProfilePic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageContract.launch(intent)
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.edit_profile_dialog_title)
            .setView(view) // Set custom layout as dialog content view
            .setPositiveButton(R.string.save) { dialog, which ->
                // Handle save button click
                val newName = editTextProfileName.text.toString()
                // Update profile with newName if it's different from the current name
                if (newName != currentName) {
//                     // Call function to update user name
//                    // viewModel.updateUserName(userMetaData, newName)
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, which ->
                // Handle cancel button click
            }
            .create()
    }
}


