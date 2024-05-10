package com.sopt.now

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    val binding: T
        get() = requireNotNull(_binding) { "_binding이 null이 아닌 경우만 _binding 반환" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = fragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun fragmentBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}