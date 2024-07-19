package com.bugbender.memepick.favorites.notlogged

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bugbender.memepick.favorites.databinding.FragmentFavoritesNotLoggedBinding
import com.bugbender.memepick.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesNotLoggedFragment : BaseFragment<FragmentFavoritesNotLoggedBinding>() {

    private val viewModel: FavoritesNotLoggedViewModel by viewModels()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoritesNotLoggedBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToLoginButton.setOnClickListener {
            viewModel.goToAuthentication()
        }
    }
}