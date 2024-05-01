package com.sopt.now

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding:FragmentHomeBinding
        get()= requireNotNull(_binding){"_binding이 null이 아닌 경우만 _binding 반환"}
    private var _binding: FragmentHomeBinding ?= null
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeListAdapter = HomeListAdapter()
        binding.rvFriends.run {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        homeListAdapter.setHomeList(viewModel.homeListData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}