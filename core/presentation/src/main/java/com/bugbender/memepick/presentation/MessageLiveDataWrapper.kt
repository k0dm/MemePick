package com.bugbender.memepick.presentation

import android.util.Log
import javax.inject.Inject

interface MessageLiveDataWrapper : LiveDataWrapper<String> {

    class Base @Inject constructor() : MessageLiveDataWrapper, LiveDataWrapper.Single<String>() {
        init {
            Log.d("k0dm", "MessageLiveDataWrapper ${hashCode()}")
        }
    }
}