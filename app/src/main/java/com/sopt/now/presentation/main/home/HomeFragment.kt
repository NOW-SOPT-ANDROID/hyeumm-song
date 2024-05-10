package com.sopt.now.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.BindingFragment
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    override fun fragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeListAdapter = HomeListAdapter()
        binding.rvFriends.run {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        homeListAdapter.setHomeList(viewModel.homeListData)
    }
}