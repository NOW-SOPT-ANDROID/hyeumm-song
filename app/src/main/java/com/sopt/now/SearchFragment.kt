package com.sopt.now

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val binding: FragmentSearchBinding
        get()= requireNotNull(_binding){"_binding이 null이 아닌 경우만 _binding 반환"}
    private var _binding: FragmentSearchBinding?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}