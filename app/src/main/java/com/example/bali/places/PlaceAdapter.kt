package com.example.bali.places

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bali.database.entities.Place
import com.example.bali.databinding.FragmentPlaceBinding
import com.squareup.picasso.Picasso

//import com.bumptech.glide.Glide

class PlaceAdapter(private val clickListener: PlaceClickListener) : ListAdapter<Place, PlaceAdapter.PlaceViewHolder>(PlaceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = FragmentPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)
    }

    class PlaceViewHolder(private val binding: FragmentPlaceBinding, private val clickListener: PlaceClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.textViewPlaceName.text = place.name
            binding.textViewPlaceAddress.text = place.address

            Picasso.get().invalidate(place.placePhoto)
            Picasso.get()
                .load(place.placePhoto)
                .into(binding.imageViewPhoto as ImageView)

            // Set up click listener for the "Add Comment" button
            binding.buttonAddComment.setOnClickListener {
                clickListener.onAddCommentClicked(place.name)
            }
        }
    }

    interface PlaceClickListener {
        fun onAddCommentClicked(placeName: String)
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