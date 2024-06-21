package com.bugbender.memepick.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bugbender.memepick.authentication.databinding.FragmentAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment<FragmentAuthenticationBinding>() {

    private val viewModel: AuthenticationViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentAuthenticationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityContext = requireContext()
        binding.signWithGoogleLL.setOnClickListener {
            viewModel.singInWithGoogle(activityContext)
        }

        viewModel.liveData().observe(viewLifecycleOwner) { message ->
            Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}