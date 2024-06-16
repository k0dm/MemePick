package com.bugbender.memepick.presentation

import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper<T : Any> : UpdateUi<T>, ProvideLiveData<T> {

    abstract class Base<T : Any>(
        protected val liveData: MutableLiveData<T> = MutableLiveData()
    ) : LiveDataWrapper<T> {

        override fun updateUi(value: T) {
            liveData.value = value
        }

        override fun liveData() = liveData
    }

    abstract class Single<T : Any>(
        liveData: MutableLiveData<T> = SingleLiveEvent()
    ) : Base<T>(liveData)
}