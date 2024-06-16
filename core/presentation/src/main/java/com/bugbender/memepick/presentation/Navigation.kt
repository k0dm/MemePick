package com.bugbender.memepick.presentation

import javax.inject.Inject
import javax.inject.Singleton

interface Navigation {

    interface Update : UpdateUi<Screen>

    interface Mutable: Update, ProvideLiveData<Screen>

    @Singleton
    class Base @Inject constructor(): Mutable, LiveDataWrapper.Single<Screen>()
}