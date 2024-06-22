package com.bugbender.memepick.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bugbender.memepick.favorites.adapter.FavoritesAdapter
import com.bugbender.memepick.favorites.databinding.FragmentFavoritesBinding
import com.bugbender.memepick.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoritesAdapter(viewModel = viewModel)
        binding.favoriteMemesRecyclerView.adapter = adapter

        viewModel.liveData().observe(viewLifecycleOwner) { uiState ->
            uiState.show(adapter)
        }

        viewModel.getAllMemes()
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFavoritesBinding.inflate(inflater, container, false)
}

