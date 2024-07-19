package com.bugbender.memepick.memes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bugbender.memepick.memes.databinding.FragmentMemesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemesFragment : Fragment() {

    private var _binding: FragmentMemesBinding? = null
    private val binding: FragmentMemesBinding get() = _binding!!
    private val viewModel: MemesViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        viewModel.checkIsUserLoggedIn()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.memeButton.setOnClickListener {
            viewModel.getMeme()
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.changeFavorite()
        }

        viewModel.liveData().observe(viewLifecycleOwner) { uiState->
            uiState.show(binding)
        }

        viewModel.init(isFirstRun = savedInstanceState == null)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}