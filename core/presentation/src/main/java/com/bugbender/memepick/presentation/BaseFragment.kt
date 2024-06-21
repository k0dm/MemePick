package com.bugbender.memepick.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container)
        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}