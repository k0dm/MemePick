package com.bugbender.memepick.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bugbender.memepick.authentication.databinding.FragmentAuthenticationBinding
import com.bugbender.memepick.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment<FragmentAuthenticationBinding>() {

    @Inject
    lateinit var factory: AuthenticationViewModel.Factory

    @Inject
    @ToProfileMapper
    lateinit var toProfileMapperProvider: Provider<SignInAuthResultMapper>

    @Inject
    @ToFavoritesMapper
    lateinit var toFavoriteMapperProvider: Provider<SignInAuthResultMapper>

    private val viewModel: AuthenticationViewModel by viewModels {
        AuthenticationViewModel.provideViewModel(
            when (val className = requireArguments().getString(MAPPER_CLASS_NAME)!!) {
                SignInAuthResultMapper.ToProfile::class.simpleName -> toProfileMapperProvider.get()
                SignInAuthResultMapper.ToFavorites::class.simpleName -> toFavoriteMapperProvider.get()
                else -> throw IllegalStateException("No such mapper with class: $className")
            }, factory
        )
    }

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

    companion object {
        private const val MAPPER_CLASS_NAME = "SignInAuthResultMapperClassName"

        fun newInstance(mapperClassName: String) =
            AuthenticationFragment().apply {
                arguments = Bundle().apply {
                    putString(MAPPER_CLASS_NAME, mapperClassName)
                }
            }
    }
}