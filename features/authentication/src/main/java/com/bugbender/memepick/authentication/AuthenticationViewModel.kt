package com.bugbender.memepick.authentication

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bugbender.memepick.presentation.BaseViewModel
import com.bugbender.memepick.presentation.MessageLiveDataWrapper
import com.bugbender.memepick.presentation.ProvideLiveData
import com.bugbender.memepick.presentation.RunAsync
import com.bugbender.mempick.core.firebase.AuthRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AuthenticationViewModel @AssistedInject constructor(
    private val repository: AuthRepository.LoginWithGoogle,
    private val messageLiveDataWrapper: MessageLiveDataWrapper,
    @Assisted private val mapper: SignInAuthResultMapper,
    runAsync: RunAsync
) : BaseViewModel(runAsync), ProvideLiveData<String> {

    init {
        Log.d("k0dm", "vm${hashCode()} with mapper: ${mapper::class.simpleName}")
    }
    fun singInWithGoogle(activity: Context) = runAsync({
        repository.loginWithGoogle(activity)
    }) { authResult ->
        authResult.map(mapper)
    }

    override fun liveData() = messageLiveDataWrapper.liveData()

    @AssistedFactory
    interface Factory {

        fun create(mapper: SignInAuthResultMapper): AuthenticationViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun provideViewModel(
            mapper: SignInAuthResultMapper,
            factory: Factory,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(mapper) as T
            }
        }
    }
}