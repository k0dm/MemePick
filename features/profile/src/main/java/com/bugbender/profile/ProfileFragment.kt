package com.bugbender.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bugbender.memepick.presentation.BaseFragment
import com.bugbender.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel: ProfileViewModel by viewModels()


    override fun onStart() {
        super.onStart()
        viewModel.checkIsUserLoggedIn()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding){
        super.onViewCreated(view, savedInstanceState)

        userEmailTextView.text = viewModel.userEmail()

        logOutButton.setOnClickListener {
            viewModel.logOut()
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentProfileBinding.inflate(inflater, container, false)
}