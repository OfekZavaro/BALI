package com.example.bali.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bali.database.entities.Place
import com.example.bali.databinding.FragmentPlaceBinding
//import com.bumptech.glide.Glide

class PlaceAdapter : ListAdapter<Place, PlaceAdapter.PlaceViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = FragmentPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)
    }

    class PlaceViewHolder(private val binding: FragmentPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.textViewPlaceName.text = place.name
            binding.textViewPlaceAddress.text = place.address
            // If you're using Glide to load images:
            // Glide.with(binding.imageViewPhoto.context).load(place.placePhoto).into(binding.imageViewPhoto)
        }
    }
}

class PlaceDiffCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem == newItem
    }
}