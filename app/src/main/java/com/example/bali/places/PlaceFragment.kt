package com.example.bali.places


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bali.R
import com.example.bali.database.DatabaseInstance
//import com.bumptech.glide.Glide
import com.example.bali.databinding.FragmentPlaceBinding
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class PlaceFragment : Fragment() {

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlaceViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("placeId")?.let { placeId ->
            viewModel.getPlaceById(placeId).observe(viewLifecycleOwner) { place ->
                place?.let {
                    binding.textViewPlaceName.text = it.name
                    binding.textViewPlaceAddress.text = it.address
                    binding.textViewPlaceDescription.text = it.description
                    /*
                    Picasso.get().invalidate(it.placePhoto)
                    Picasso.get()
                        .load(it.placePhoto)
                        .into(binding.imageViewPhoto as ImageView)*/
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
