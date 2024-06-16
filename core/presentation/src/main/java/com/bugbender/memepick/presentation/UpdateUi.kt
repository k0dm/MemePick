package com.bugbender.memepick.presentation

interface UpdateUi<T: Any> {

    fun updateUi(value: T)
}