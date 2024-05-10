package com.sopt.now.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sopt.now.BindingFragment
import com.sopt.now.databinding.FragmentSearchBinding

class SearchFragment : BindingFragment<FragmentSearchBinding>() {
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

}