package com.bugbender.memepick.authentication

import com.bugbender.memepick.presentation.Screen

class AuthenticationScreen(
    private val mapperClassName: String
) : Screen.Replace(AuthenticationFragment::class.java) {

    override fun fragment() = AuthenticationFragment.newInstance(mapperClassName)
}