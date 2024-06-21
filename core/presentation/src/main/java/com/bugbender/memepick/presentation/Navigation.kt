package com.bugbender.memepick.presentation

interface Navigation {

    interface Update : UpdateUi<Screen> {

        fun goToAuthentication()

        fun goToProfile()
    }

    interface Mutable: Update, ProvideLiveData<Screen>
}