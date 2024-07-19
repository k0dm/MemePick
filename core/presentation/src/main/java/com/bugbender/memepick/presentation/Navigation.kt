package com.bugbender.memepick.presentation

interface Navigation {

    interface Update : UpdateUi<Screen> {

        fun goToAuthenticationFromProfile()

        fun goToProfile()

        fun goToFavoritesNotLogged()

        fun goToFavorites()

        fun goToAuthenticationFromFavorites()
    }

    interface Mutable : Update, ProvideLiveData<Screen>
}