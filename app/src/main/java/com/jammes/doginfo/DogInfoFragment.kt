package com.jammes.doginfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jammes.doginfo.databinding.FragmentDogInfoBinding

class DogInfoFragment: Fragment() {

    private var _binding: FragmentDogInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogInfoBinding.inflate(inflater, container, false)

        return (binding.root)
    }
}