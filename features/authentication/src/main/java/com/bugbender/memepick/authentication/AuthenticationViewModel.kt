package com.bugbender.memepick.authentication

import android.content.Context
import androidx.lifecycle.LiveData
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.LiveDataWrapper
import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import com.bugbender.memepick.presentation.Navigation
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val repository: AuthRepository.LoginWithGoogle,
    private val messageLiveDataWrapper: MessageLiveDataWrapper,
    private val mapper: SignInAuthResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<String> {

    fun singInWithGoogle(activity: Context) = runAsync({
        repository.loginWithGoogle(activity)
    }) { authResult ->
        authResult.map(mapper)
    }

    override fun liveData() = messageLiveDataWrapper.liveData()
}