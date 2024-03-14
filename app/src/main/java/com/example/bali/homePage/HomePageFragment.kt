package com.example.bali.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bali.databinding.FragmentHomePageBinding
import com.example.bali.places.PlaceAdapter
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your adapter
        val placeAdapter = PlaceAdapter()

        // Setup RecyclerView
        binding.recyclerViewPlaces.apply {
            adapter = placeAdapter
            layoutManager = LinearLayoutManager(context)
        }

        // Observe changes in the database and submit list to adapter
        viewModel.places.observe(viewLifecycleOwner) { places ->
            placeAdapter.submitList(places)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
